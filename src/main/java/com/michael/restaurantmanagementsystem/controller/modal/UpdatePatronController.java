package com.michael.restaurantmanagementsystem.controller.modal;

import com.michael.restaurantmanagementsystem.service.Utils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class UpdatePatronController {
    @FXML
    public MFXButton submitBtn, deleteBtn;

    public void handleClick(ActionEvent actionEvent) {
        if (actionEvent.getSource() == submitBtn) {
            Utils.showAlert("Confirm update", "Click Ok to update Patron", Alert.AlertType.CONFIRMATION);
        } else if (actionEvent.getSource() == deleteBtn) {
        }
        //exit modal window
        Stage dialog = (Stage) (((Node) actionEvent.getSource()).getScene().getWindow());
        dialog.hide();
    }
}
