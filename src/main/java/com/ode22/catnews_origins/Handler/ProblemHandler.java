package com.ode22.catnews_origins.Handler;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Class containing all logic to communicate occurring failures to user.
 */
public class ProblemHandler {

    /**
     * Creates an alert to display information via a pop-up field.
     * @param message the message to display on the screen
     */
    public void info(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Creates an alert to display information via a pop-up field.
     * @param message the message to display on the screen
     */
    public void error(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
