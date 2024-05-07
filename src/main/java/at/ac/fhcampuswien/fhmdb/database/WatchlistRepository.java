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

    public void addToWatchlist(Movie movie) throws SQLException {
        String title = movie.getTitle().replace("'", "''");
        if (dao.queryForEq("title", title).isEmpty()) {
            dao.create(movieToEntity(movie));
            System.out.println("Added " + movie.getTitle() + " to Watchlist");
        }
    }

    public void removeFromWatchlist(Movie movie) throws SQLException {
        String title = movie.getTitle().replace("'", "''");
        List<MovieEntity> movies = dao.queryForEq("title", title);
        if (!movies.isEmpty()) {
            dao.delete(movies);
            System.out.println("Deleted " + movie.getTitle() + " from Watchlist");
        }
    }

    private MovieEntity movieToEntity(Movie movie)
    {
        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getDescription(), MovieEntity.genresToString(movie.getGenres()), movie.getReleaseYear(), movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
    }
}

