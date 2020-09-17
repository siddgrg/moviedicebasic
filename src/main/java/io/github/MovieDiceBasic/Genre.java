package io.github.MovieDiceBasic;

enum Genre {
    ACTION(28, "Action"),
    ADVENTURE(12, "Adventure"),
    ANIMATION(16, "Animation"),
    COMEDY(35, "Comedy"),
    CRIME(80, "Crime"),
    DOCUMENTARY(99, "Documentary"),
    DRAMA(18, "Drama"),
    FAMILY(10751, "Family"),
    FANTASY(14, "Fantasy"),
    HISTORY(36, "History"),
    HORROR(27, "Horror"),
    MUSIC(10402, "Music"),
    MYSTERY(9648, "Mystery"),
    ROMANCE(10749, "Romance"),
    SCIENCE_FICTION(878, "Science Fiction"),
    TV_MOVIE(10770, "TV Movie"),
    THRILLER(53, "Thriller");

    private Genre(final int id, final String genre) {
        this.id = id;
        this.genre = genre;
    }

    private final int id;
    private final String genre;

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public static String findById(int id) {
        for (Genre g : values()) {
            if (g.getId() == id) {
                return g.getGenre();
            }
        }
        return "Unknown";
    }

    @Override
    public String toString() {
        return "Genre{" +
            "id=" + id +
            ", genre='" + genre + '\'' +
            '}';
    }
}
