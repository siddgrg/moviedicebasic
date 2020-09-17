package io.github.MovieDiceBasic;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class TmdbApiConfigurationBody {
    private final ImagesBody images;
    private final String[] changeKeys;

    public TmdbApiConfigurationBody(
        @JsonProperty("images") ImagesBody images,
        @JsonProperty("change_keys") String[] changeKeys) {
        this.images = images;
        this.changeKeys = changeKeys;
    }

    public ImagesBody getImages() {
        return images;
    }

    public String[] getChangeKeys() {
        return changeKeys;
    }

    public static class ImagesBody {
        private final String baseUrl;
        private final String secureBaseUrl;
        private final String[] backdropSizes;
        private final String[] logoSizes;
        private final String[] posterSizes;
        private final String[] profileSizes;
        private final String[] stillSizes;

        public ImagesBody(
            @JsonProperty("base_url") String baseUrl,
            @JsonProperty("secure_base_url") String secureBaseUrl,
            @JsonProperty("backdrop_sizes") String[] backdropSizes,
            @JsonProperty("logo_sizes") String[] logoSizes,
            @JsonProperty("poster_sizes") String[] posterSizes,
            @JsonProperty("profile_sizes") String[] profileSizes,
            @JsonProperty("still_sizes") String[] stillSizes) {
            this.baseUrl = baseUrl;
            this.secureBaseUrl = secureBaseUrl;
            this.backdropSizes = backdropSizes;
            this.logoSizes = logoSizes;
            this.posterSizes = posterSizes;
            this.profileSizes = profileSizes;
            this.stillSizes = stillSizes;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public String getSecureBaseUrl() {
            return secureBaseUrl;
        }

        public String[] getBackdropSizes() {
            return backdropSizes;
        }

        public String[] getLogoSizes() {
            return logoSizes;
        }

        public String[] getPosterSizes() {
            return posterSizes;
        }

        public String[] getProfileSizes() {
            return profileSizes;
        }

        public String[] getStillSizes() {
            return stillSizes;
        }

        @Override
        public String toString() {
            return "ImagesBody{" +
                "baseUrl='" + baseUrl + '\'' +
                ", secureBaseUrl='" + secureBaseUrl + '\'' +
                ", backdropSizes=" + Arrays.toString(backdropSizes) +
                ", logoSizes=" + Arrays.toString(logoSizes) +
                ", posterSizes=" + Arrays.toString(posterSizes) +
                ", profileSizes=" + Arrays.toString(profileSizes) +
                ", stillSizes=" + Arrays.toString(stillSizes) +
                '}';
        }
    }
}
