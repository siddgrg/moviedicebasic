package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class MovieWatchListBody {
    private final int page;
    private final int totalResults;
    private final int totalPages;
    private final Movie[] results;
    private final String statusMessage;
    private final String errorMessage;
    private final boolean success;
    private final int statusCode;

    public MovieWatchListBody(
        @JsonProperty("page") int page,
        @JsonProperty("total_results") int totalResults,
        @JsonProperty("total_pages") int totalPages,
        @JsonProperty("results") Movie[] results,
        @JsonProperty("status_message") String statusMessage,
        @JsonProperty("error_message") String errorMessage,
        @JsonProperty("success") boolean success,
        @JsonProperty("status_code") int statusCode) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
        this.statusMessage = statusMessage;
        this.errorMessage = errorMessage;
        this.success = success;
        this.statusCode = statusCode;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Movie[] getResults() {
        return results;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "MovieWatchListBody{" +
            "page=" + page +
            ", totalResults=" + totalResults +
            ", totalPages=" + totalPages +
            ", results=" + Arrays.toString(results) +
            ", statusMessage='" + statusMessage + '\'' +
            ", errorMessage='" + errorMessage + '\'' +
            ", success=" + success +
            ", statusCode=" + statusCode +
            '}';
    }
}
