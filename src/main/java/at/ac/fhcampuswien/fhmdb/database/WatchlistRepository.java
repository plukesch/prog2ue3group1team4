package at.ac.fhcampuswien.fhmdb.database;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class WatchlistRepository {
    private final Dao<WatchlistMovieEntity, Long> dao;

    public WatchlistRepository(Dao<WatchlistMovieEntity, Long> dao) {
        this.dao = dao;
    }

    public List<WatchlistMovieEntity> getWatchlist() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            System.err.println("Error fetching the watchlist: " + e.getMessage());
            return Collections.emptyList();  // Return an empty list on error
        }
    }

    public int addToWatchlist(WatchlistMovieEntity movie) {
        try {
            return dao.create(movie);
        } catch (SQLException e) {
            System.err.println("Error adding movie to watchlist: " + e.getMessage());
            return 0;  // Return 0 to indicate failure
        }
    }

    public int removeFromWatchlist(String apiId) {
        try {
            List<WatchlistMovieEntity> entries = dao.queryForEq("apiId", apiId);
            if (!entries.isEmpty()) {
                return dao.delete(entries);
            }
            return 0;  // No entries found to delete
        } catch (SQLException e) {
            System.err.println("Error removing movie from watchlist: " + e.getMessage());
            return 0;  // Return 0 to indicate failure
        }
    }
}

