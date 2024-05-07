package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.Exceptions.DatalayerException;
import at.ac.fhcampuswien.fhmdb.database.MovieEntity;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WatchlistController {
    @FXML
    public VBox mainPane;
    @FXML
    public JFXListView movieWatchlistView;
    private WatchlistRepository repository = new WatchlistRepository();

    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem, isWatchlistCell, addToWatchlistBtn) -> {
        if (isWatchlistCell) {
            try {
                repository.removeFromWatchlist((Movie)clickedItem);
                FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("watchlist-view.fxml"));
                Parent root = FXMLLoader.load(fxmlLoader.getLocation());
                Scene scene = addToWatchlistBtn.getScene();
                scene.setRoot(root);
            } catch (SQLException e) {
                MovieCell.showExceptionDialog(new DatalayerException("Error by deleting movies"));
            } catch (IOException e) {
                MovieCell.showExceptionDialog(new IllegalArgumentException("Fxml cannot be loaded"));
            }
        } else {
            try {
                repository.addToWatchlist((Movie)clickedItem);
            } catch (SQLException e) {
                MovieCell.showExceptionDialog(new DatalayerException("Error by adding to watchlist"));
            }
        }
    };

    public void initialize() {
        System.out.println("WatchlistController initialized");

        List<MovieEntity> watchlist = new ArrayList<>();

        watchlist = repository.getWatchlist();

        ObservableList<Movie> movies = FXCollections.observableArrayList(
                watchlist.stream()
                        .map(MovieEntity::toMovie)
                        .collect(Collectors.toList())
        );

        movieWatchlistView.setItems(movies);
        movieWatchlistView.setCellFactory(movieListView -> new MovieCell(true, onAddToWatchlistClicked));
    }

    public void loadHomeView() {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), 890, 620);
            Stage stage = (Stage)mainPane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            MovieCell.showExceptionDialog(new IllegalArgumentException("Error while loading"));
        }
    }
}
