package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorisationRequestTokenBody {
    private final int statusCode;
    private final String statusMessage;
    private final boolean success;
    private final String requestToken;
    private final String errorMessage;

    public AuthorisationRequestTokenBody(
        @JsonProperty("status_code") int statusCode,
        @JsonProperty("status_message") String statusMessage,
        @JsonProperty("success") boolean success,
        @JsonProperty("request_token") String requestToken,
        @JsonProperty("error_message") String errorMessage
    ) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.success = success;
        this.requestToken = requestToken;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getRequestToken() {
        return requestToken;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "AuthorisationRequestTokenBody{" +
            "statusCode=" + statusCode +
            ", statusMessage='" + statusMessage + '\'' +
            ", success=" + success +
            ", requestToken='" + requestToken + '\'' +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}
