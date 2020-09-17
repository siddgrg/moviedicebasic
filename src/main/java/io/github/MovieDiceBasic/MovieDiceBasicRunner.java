package io.github.MovieDiceBasic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class
 */
public class MovieDiceBasicRunner extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/loginScene.fxml"));
        LoginSceneController lsc = new LoginSceneController();
        lsc.setHostServices(getHostServices());
        loader.setController(lsc);

        stage.setTitle("MovieDice");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
