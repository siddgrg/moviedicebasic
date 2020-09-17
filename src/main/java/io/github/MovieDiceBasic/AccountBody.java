package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountBody {
    private final int id;
    private final String name;
    private final String username;

    public AccountBody(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("username") String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "AccountBody{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", username='" + username + '\'' +
            '}';
    }
}
