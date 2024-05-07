package at.ac.fhcampuswien.fhmdb.Exceptions;

import java.io.IOException;

public class MovieApiException extends IOException {
    public MovieApiException(String message) {
        super(message);
    }
}
