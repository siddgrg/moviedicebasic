package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAccessTokenBody {
    private final String statusMessage;
    private final String accessToken;
    private final boolean success;
    private final int statusCode;
    private final String accountId;
    private final String errorMessage;

    public UserAccessTokenBody(
        @JsonProperty("status_message") String statusMessage,
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("success") boolean success,
        @JsonProperty("status_code") int statusCode,
        @JsonProperty("account_id") String accountId,
        @JsonProperty("error_message") String errorMessage) {
        this.statusMessage = statusMessage;
        this.accessToken = accessToken;
        this.success = success;
        this.statusCode = statusCode;
        this.accountId = accountId;
        this.errorMessage = errorMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "UserAccessTokenBody{" +
            "statusMessage='" + statusMessage + '\'' +
            ", accessToken='" + accessToken + '\'' +
            ", success=" + success +
            ", statusCode=" + statusCode +
            ", accountId='" + accountId + '\'' +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}
