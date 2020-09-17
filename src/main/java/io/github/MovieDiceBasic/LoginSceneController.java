package io.github.MovieDiceBasic;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class LoginSceneController {
    private @FXML Button loginButton;
    private @FXML Button proceedButton;

    private HostServices hostServices;
    private HttpClient httpClient;
    private String requestToken;

    @FXML
    private void authenticateUser() throws IOException, InterruptedException {
        if (!userInfoFileExists()) {
            httpClient = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder(
                URI.create("https://api.themoviedb.org/4/auth/request_token"))
                .POST(BodyPublishers.noBody())
                .header("content-type", "application/json;charset=utf-8")
                .header("authorization", "Bearer " + TmdbApiKey.API_READ_ACCESS_TOKEN)
                .build();

            var response = httpClient.send(request, new JsonBodyHandler<>(AuthorisationRequestTokenBody.class));

            var authorisationRequestTokenBody = response.body().get();
            if (authorisationRequestTokenBody.isSuccess()) {
                requestToken = authorisationRequestTokenBody.getRequestToken();
                hostServices.showDocument(String.format("https://www.themoviedb.org/auth/access?request_token=%s", requestToken));
                proceedButton.setDisable(false);
            }
        } else {
            try {
                TmdbApiKey.deserialiseUserAccountInfo();
                loadMainStage();
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void checkIfUserPermissionGranted() throws IOException, InterruptedException {
        if (httpClient == null) {
            httpClient = HttpClient.newHttpClient();
        }

        var accessCodeRequest = HttpRequest.newBuilder(
            URI.create("https://api.themoviedb.org/4/auth/access_token"))
            .POST(BodyPublishers.ofString(String.format("{\n  \"request_token\": \"%s\"%n}", requestToken)))
            .header("content-type", "application/json;charset=utf-8")
            .header("authorization", "Bearer " + TmdbApiKey.API_READ_ACCESS_TOKEN)
            .build();

        var accessCodeResponse = httpClient.send(accessCodeRequest, new JsonBodyHandler<>(UserAccessTokenBody.class));

        var userAccessTokenBody = accessCodeResponse.body().get();

        var sessionIdRequest = HttpRequest.newBuilder(
            URI.create(String.format("https://api.themoviedb.org/3/authentication/session/convert/4?api_key=%s", TmdbApiKey.API_KEY)))
            .POST(BodyPublishers.ofString(String.format("{\n  \"access_token\": \"%s\"%n}", userAccessTokenBody.getAccessToken())))
            .header("content-type", "application/json")
            .build();

        var sessionIdResponse = httpClient.send(sessionIdRequest, new JsonBodyHandler<>(SessionIdBody.class));

        var sessionIdBody = sessionIdResponse.body().get();

        if (userAccessTokenBody.isSuccess() && sessionIdBody.isSuccess()) {
            try {
                TmdbApiKey.serialiseUserAccountInfo(
                    userAccessTokenBody.getAccountId(),
                    userAccessTokenBody.getAccessToken(),
                    sessionIdBody.getSessionId(),
                    getAccountDetails(sessionIdBody.getSessionId()).getUsername());
                loadMainStage();
            } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Warning - account id and user access token not set");
            System.out.printf("access token success: %s%n", userAccessTokenBody.isSuccess());
            System.out.printf("session id success: %s%n", sessionIdBody.isSuccess());
        }
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    private void loadMainStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainScene.fxml"));
        loader.setController(new MainSceneController());

        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
        stage.setTitle(String.format("MovieDice - %s", TmdbApiKey.getUsername()));
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();

        Stage loginStage = (Stage) proceedButton.getScene().getWindow();
        loginStage.close();
    }

    private boolean userInfoFileExists() {
        File f = new File(TmdbApiKey.getUserInfoFilePath());
        return f.exists();
    }

    private AccountBody getAccountDetails(String sessionId) throws IOException, InterruptedException {
        if (httpClient == null) {
            httpClient = HttpClient.newHttpClient();
        }

        var accountRequest = HttpRequest.newBuilder(
            URI.create(String.format("https://api.themoviedb.org/3/account?api_key=%s&session_id=%s", TmdbApiKey.API_KEY, sessionId)))
            .build();

        var accountResponse = httpClient.send(accountRequest, new JsonBodyHandler<>(AccountBody.class));

        return accountResponse.body().get();
    }

}
