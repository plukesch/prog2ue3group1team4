package at.ac.fhcampuswien.fhmdb;

import javafx.scene.control.Button;

@FunctionalInterface
public interface ClickEventHandler<T> {
    void onClick(T t, boolean isWatchlistCell, Button watchBtn);
}
