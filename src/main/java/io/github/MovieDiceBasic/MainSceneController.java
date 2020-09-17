package io.github.MovieDiceBasic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainSceneController {
    private @FXML Button movieRollButton;
    private @FXML Label titleLabel;
    private @FXML Label languageLabel;
    private @FXML Label releaseDateLabel;
    private @FXML Label averageVoteLabel;
    private @FXML Label voteCountLabel;
    private @FXML Label idLabel;
    private @FXML ImageView posterImageView;
    private @FXML ImageView backdropImageView;
    private @FXML Label genresLabel;

    private HttpClient httpClient;
    private List<Movie> movieWatchList;
    private final String WATCH_LIST_URI = String.format("https://api.themoviedb.org/4/account/%s/movie/watchlist", TmdbApiKey.getAccountId());
    private String posterImageUrlBase;
    private String backdropImageUrlBase;

    @FXML
    private void diceRollNoParameters() throws IOException, InterruptedException {
        if (movieWatchList == null) {
            httpClient = HttpClient.newHttpClient();

            var request = HttpRequest.newBuilder(
                URI.create(WATCH_LIST_URI))
                .header("authorization", "Bearer " + TmdbApiKey.getUserAccessToken())
                .build();

            var response = httpClient.send(request, new JsonBodyHandler<>(MovieWatchListBody.class));

            var movieWatchListBody = response.body().get();
            movieWatchList = new ArrayList<Movie>();

            for (int i = 1; i <= movieWatchListBody.getTotalPages(); i++) {
                movieWatchList.addAll(Arrays.asList(getMoviesFromOnePageFromWatchlist(i)));
            }

            System.out.printf("Watch list length: %d\n", movieWatchList.size());
            posterImageUrlBase = getImageUrlBase("poster");
            backdropImageUrlBase = getImageUrlBase("backdrop");
        }

        Random random = new Random();
        var movie = movieWatchList.get(random.nextInt(movieWatchList.size()));
        setGuiMovieDetails(movie);
    }

    private Movie[] getMoviesFromOnePageFromWatchlist(int page) throws IOException, InterruptedException {
        if (httpClient == null) {
            httpClient = HttpClient.newHttpClient();
        }

        var request = HttpRequest.newBuilder(
            URI.create(WATCH_LIST_URI + "?page=" + page))
            .header("authorization", "Bearer " + TmdbApiKey.getUserAccessToken())
            .build();

        var response = httpClient.send(request, new JsonBodyHandler<>(MovieWatchListBody.class));

        return response.body().get().getResults();
    }

    @NotNull
    private String getImageUrlBase(String type) throws IOException, InterruptedException {
        if (httpClient == null) {
            httpClient = HttpClient.newHttpClient();
        }

        var request = HttpRequest.newBuilder(
            URI.create("https://api.themoviedb.org/3/configuration?api_key=" + TmdbApiKey.API_KEY))
            .build();

        var response = httpClient.send(request, new JsonBodyHandler<>(TmdbApiConfigurationBody.class));

        var configurationBody = response.body().get();
        StringBuilder imageUri = new StringBuilder();
        imageUri.append(configurationBody.getImages().getSecureBaseUrl());
        if ("poster".equalsIgnoreCase(type)) {
            imageUri.append(configurationBody.getImages().getPosterSizes()[3]);
        } else if ("backdrop".equalsIgnoreCase(type)) {
            imageUri.append(configurationBody.getImages().getBackdropSizes()[1]);
            System.out.println("Backdrop size: " + configurationBody.getImages().getBackdropSizes()[1]);
        }
        imageUri.append("/");

        return imageUri.toString();
    }

    private void setGuiMovieDetails(Movie movie) {
        titleLabel.setText(movie.getTitle());
        languageLabel.setText(movie.getOriginalLanguage());
        releaseDateLabel.setText(movie.getReleaseDate());
        averageVoteLabel.setText(String.valueOf(movie.getVoteAverage()));
        voteCountLabel.setText(String.valueOf(movie.getVoteCount()));
        idLabel.setText(String.valueOf(movie.getId()));
        posterImageView.setImage(new Image(posterImageUrlBase + movie.getPosterPath()));
        backdropImageView.setImage(new Image(backdropImageUrlBase + movie.getBackdropPath()));
        genresLabel.setText(setGenreLabel(movie));
    }

    private String setGenreLabel(Movie movie) {
        int[] genresInMovie = movie.getGenreIds();
        if (genresInMovie.length < 2) {
            return Genre.findById(genresInMovie[0]);
        } else {
            String genresString = Arrays.stream(genresInMovie)
                .mapToObj(Genre::findById)
                .filter(g -> !(g.equals("Unknown")))
                .map(g -> g + ", ")
                .collect(Collectors.joining());
            return genresString.substring(0, genresString.length() - 2);
        }
    }
}
