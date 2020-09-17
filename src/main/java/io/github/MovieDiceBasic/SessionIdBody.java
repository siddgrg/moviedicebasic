package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionIdBody {
    private final boolean success;
    private final String sessionId;
    private final String statusMessage;
    private final int statusCode;

    public SessionIdBody(
        @JsonProperty("success") boolean success,
        @JsonProperty("session_id") String sessionId,
        @JsonProperty("status_message") String statusMessage,
        @JsonProperty("status_code") int statusCode) {
        this.success = success;
        this.sessionId = sessionId;
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return "SessionIdBody{" +
            "success=" + success +
            ", sessionId='" + sessionId + '\'' +
            ", statusMessage='" + statusMessage + '\'' +
            ", statusCode=" + statusCode +
            '}';
    }
}
