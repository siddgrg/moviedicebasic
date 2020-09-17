package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Movie {
    private final double popularity;
    private final int voteCount;
    private final boolean video;
    private final String posterPath;
    private final int id;
    private final boolean adult;
    private final String backdropPath;
    private final String originalLanguage;
    private final String originalTitle;
    private final int[] genreIds;
    private final String title;
    private final double voteAverage;
    private final String overview;
    private final String releaseDate;

    public Movie(
        @JsonProperty("popularity") double popularity,
        @JsonProperty("vote_count") int voteCount,
        @JsonProperty("video") boolean video,
        @JsonProperty("poster_path") String posterPath,
        @JsonProperty("id") int id,
        @JsonProperty("adult") boolean adult,
        @JsonProperty("backdrop_path") String backdropPath,
        @JsonProperty("original_language") String originalLanguage,
        @JsonProperty("original_title") String originalTitle,
        @JsonProperty("genre_ids") int[] genreIds,
        @JsonProperty("title") String title,
        @JsonProperty("vote_average") double voteAverage,
        @JsonProperty("overview") String overview,
        @JsonProperty("release_date") String releaseDate) {
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.posterPath = posterPath;
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.title = title;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int[] getGenreIds() {
        return genreIds;
    }

    public String getTitle() {
        return title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "popularity=" + popularity +
            ", voteCount=" + voteCount +
            ", video=" + video +
            ", posterPath='" + posterPath + '\'' +
            ", id=" + id +
            ", adult=" + adult +
            ", backdropPath='" + backdropPath + '\'' +
            ", originalLanguage='" + originalLanguage + '\'' +
            ", originalTitle='" + originalTitle + '\'' +
            ", genreIds=" + Arrays.toString(genreIds) +
            ", title='" + title + '\'' +
            ", voteAverage=" + voteAverage +
            ", overview='" + overview + '\'' +
            ", releaseDate='" + releaseDate + '\'' +
            '}';
    }
}
