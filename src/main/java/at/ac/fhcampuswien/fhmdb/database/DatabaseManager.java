package at.ac.fhcampuswien.fhmdb.database;
import at.ac.fhcampuswien.fhmdb.Exceptions.DatalayerException;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseManager {
    private static String DB_URL = "jdbc:h2:file: ./db/moviesDB";
    private static String username = "User";  //
    private static String password = "Password";  //
    private static ConnectionSource conn;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;
    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            createConnectionSource();
            movieDao = DaoManager.createDao(conn, MovieEntity.class);
            createTables();
        } catch (SQLException e) {
            MovieCell.showExceptionDialog(new DatalayerException("There is a problem in creating the Database."));
        }
    }
    public static DatabaseManager getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private static void createConnectionSource() throws SQLException {
        conn = new JdbcConnectionSource(DB_URL, username, password);
    }

    public ConnectionSource getConnectionSource() {
        return conn;
    }

    public Dao<MovieEntity, Long> getMovieDao() {
        if (movieDao == null) {
            try {
                movieDao = DaoManager.createDao(conn, MovieEntity.class);
            } catch (SQLException e) {
                System.err.println("Error creating movie DAO: " + e.getMessage());
            }
        }
        return movieDao;
    }

    private void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(conn, MovieEntity.class);
    }

    public Dao<MovieEntity, Long> getDao() {
        return this.movieDao;
    }
}


