package at.ac.fhcampuswien.fhmdb.database;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@DatabaseTable(tableName = "movies")
public class MovieEntity {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String apiId;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;  // Comma-separated genres

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private String imgUrl;

    @DatabaseField
    private int lengthInMinutes;

    @DatabaseField
    private double rating;

    // Constructors, getters and setters
    public MovieEntity() {
        // ORMLite needs a no-arg constructor
    }

    public MovieEntity(String apiId, String title, String description, String genres, int releaseYear, String imgUrl, int lengthInMinutes, double rating) {
        this.apiId = apiId;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getApiId() {
        return apiId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenres() {
        return genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    // Additional methods to handle conversion between List and comma-separated String
    public List<String> getGenresList() {
        return List.of(genres.split(","));
    }

    public void setGenresList(List<String> genres) {
        this.genres = String.join(",", genres);
    }

    public static String genresToString(List<Genre> genres) {

        String result = "";
        for (var item: genres) {
            result += item.toString() + ",";
        }
        return result;
    }

    public Movie toMovie() {
        List<Genre> genres = Arrays.stream(this.genres.split(","))
                .map(Genre::valueOf)
                .collect(Collectors.toList());
        return new Movie(apiId, title, description, genres, releaseYear, imgUrl, lengthInMinutes, rating);
    }
}




