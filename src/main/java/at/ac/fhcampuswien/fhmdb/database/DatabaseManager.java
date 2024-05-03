package at.ac.fhcampuswien.fhmdb.database;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import java.sql.SQLException;

public class DatabaseManager {
    private String DB_URL = "jdbc:h2:mem:fhmdb";
    private String username = "";  // If required
    private String password = "";  // If required
    private ConnectionSource conn;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    public DatabaseManager() {
        createConnectionSource();
        createTables();  // You would implement this to create your tables if not exist
    }

    public void createConnectionSource() {
        try {
            conn = new JdbcConnectionSource(DB_URL, username, password);
        } catch (SQLException e) {
            System.err.println("Error creating the database connection: " + e.getMessage());
        }
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

    public Dao<WatchlistMovieEntity, Long> getWatchlistDao() {
        if (watchlistDao == null) {
            try {
                watchlistDao = DaoManager.createDao(conn, WatchlistMovieEntity.class);
            } catch (SQLException e) {
                System.err.println("Error creating watchlist DAO: " + e.getMessage());
            }
        }
        return watchlistDao;
    }

    // Assume this method creates tables if they do not exist
    private void createTables() {
        // Implement using ORMLite table utilities or direct SQL executions
    }
}


