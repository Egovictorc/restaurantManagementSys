package com.michael.restaurantmanagementsystem.controller;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.service.Utils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.Arrays;

public class SignupController {
    @FXML
    public MFXButton btnLogin, btnSignup;

    @FXML
    public MFXTextField txtEmail, txtFirstName, txtLastName;
    @FXML
    public MFXPasswordField txtPassword;

    @FXML
    public MFXRadioButton staff, patron;

    @FXML
    public ToggleGroup userCategory;

    public void handleClick(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == btnSignup) {
            String email = txtEmail.getText();
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String password = txtPassword.getText();
            if (validateFields(firstName, lastName, email, password)) {
                Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
                Main.setRoot(userCategory.getSelectedToggle() == patron ? "dashboard/dashboard-patron" : "dashboard/dashboard-staff", bounds);
            } else {
                Utils.showAlert("Error!! Incomplete fields", "All fields are required", Alert.AlertType.ERROR);
            }
            ;
        } else if (actionEvent.getSource() == btnLogin) {
            Main.setRoot("login");
        }
    }

    private boolean validateFields(String... fields) {
        return Arrays.stream(fields).noneMatch(String::isBlank);
    }
}
