package at.ac.fhcampuswien.fhmdb.Exceptions;

import java.sql.SQLException;

public class DatalayerException extends SQLException {
    public DatalayerException(String message) {
        super(message);
    }
}
