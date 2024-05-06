//package at.ac.fhcampuswien.fhmdb.database;
//import com.j256.ormlite.dao.Dao;
//import java.sql.SQLException;
//import java.util.Collections;
//import java.util.List;
//
//public class MovieRepository {
//    private final Dao<MovieEntity, Long> dao;
//
//    public MovieRepository(Dao<MovieEntity, Long> dao) {
//        this.dao = dao;
//    }
//
//    public List<MovieEntity> getAllMovies() {
//        try {
//            return dao.queryForAll();
//        } catch (SQLException e) {
//            System.err.println("Error fetching all movies: " + e.getMessage());
//            return Collections.emptyList();  // Return an empty list on error
//        }
//    }
//
//    public int removeAll() {
//        try {
//            return dao.deleteBuilder().delete();
//        } catch (SQLException e) {
//            System.err.println("Error removing all movies: " + e.getMessage());
//            return 0;  // Return 0 to indicate no movies were deleted on error
//        }
//    }
//
//    public MovieEntity getMovie(Long id) {
//        try {
//            return dao.queryForId(id);
//        } catch (SQLException e) {
//            System.err.println("Error fetching movie with ID: " + e.getMessage());
//            return null;  // Return null if the movie cannot be fetched
//        }
//    }
//
//    public int addAllMovies(List<MovieEntity> movies) {
//        try {
//            return dao.create(movies);  // Assuming a method that adds a list of movies; this may require iteration in reality
//        } catch (SQLException e) {
//            System.err.println("Error adding all movies: " + e.getMessage());
//            return 0;  // Return 0 to indicate no movies were added on error
//        }
//    }
//}
//
//
