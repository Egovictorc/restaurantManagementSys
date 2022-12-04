package com.michael.restaurantmanagementsystem.controller;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.service.Utils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.IOException;

public class SignupController {
    @FXML
    public MFXButton btnLogin, btnSignup;

    @FXML
    public MFXTextField txtEmail;
    @FXML
    public MFXPasswordField txtPassword;

    public void handleClick(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == btnLogin) {
            String email = txtEmail.getText();
            String password = txtPassword.getText();
            if (validateUser(email, password)) {
                Main.setRoot("home");

            } else {
                Utils.showAlert("Error", "Invalid email and / or password", Alert.AlertType.ERROR);
            }
            ;
        }
        if (actionEvent.getSource() == btnSignup) {
            Main.setRoot("signup");
        }
    }

    private boolean validateUser(String email, String password) {
        return email.equals("admin@tastyfood.com") && password.equals("12345");
    }
}
