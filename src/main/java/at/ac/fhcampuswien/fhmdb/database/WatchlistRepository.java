package at.ac.fhcampuswien.fhmdb.database;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class WatchlistRepository {
    private final Dao<MovieEntity, Long> dao;

    public WatchlistRepository()
    {
        this.dao = DatabaseManager.getInstance().getDao();
    }

    public List<MovieEntity> getWatchlist() {
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            System.err.println("Error fetching the watchlist: " + e.getMessage());
            return Collections.emptyList();  // Return an empty list on error
        }
    }

    public int addToWatchlist(MovieEntity movie) {
        try {
            return dao.create(movie);
        } catch (SQLException e) {
            System.err.println("Error adding movie to watchlist: " + e.getMessage());
            return 0;  // Return 0 to indicate failure
        }
    }

    public int removeFromWatchlist(String apiId) {
        try {
            List<MovieEntity> entries = dao.queryForEq("apiId", apiId);
            if (!entries.isEmpty()) {
                return dao.delete(entries);
            }
            return 0;  // No entries found to delete
        } catch (SQLException e) {
            System.err.println("Error removing movie from watchlist: " + e.getMessage());
            return 0;  // Return 0 to indicate failure
        }
    }

    private MovieEntity movieToEntity(Movie movie)
    {
        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getDescription(), MovieEntity.genresToString(movie.getGenres()), movie.getReleaseYear(), movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
    }
}

