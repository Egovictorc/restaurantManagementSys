package com.michael.restaurantmanagementsystem.service;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Utils {
    public static Alert alert;

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        //alert.setAlertType(Alert.AlertType.ERROR);
        alert.show();
    }

    public static void useDeviceSize(Stage stage, Rectangle2D bounds) {
        stage.setY(0.0);
        stage.setX(0.0);
        //set stage to full size
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }
}
