package com.michael.restaurantmanagementsystem.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PatronController {

    @FXML
    private MFXButton patronBtn;

    @FXML
    private Label email, status;

    public void initialize() {

    }


    public void handleClick(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(new FXMLLoader(PatronController.class.getResource("fxml/modal/update-patron.fxml")).load());
        Stage dialog = new Stage();
        // populate dialog with controls.
        Stage parentStage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        dialog.initOwner(parentStage);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }
}
