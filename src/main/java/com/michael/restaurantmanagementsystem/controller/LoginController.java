package com.michael.restaurantmanagementsystem.controller;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.service.Utils;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.stage.Screen;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

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
                logger.log(Level.INFO, "User {} logged in successfully", email);
                //Rectangle2D bounds = Screen.getPrimary().getBounds();
                Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
                Main.setRoot("home", bounds);

            } else {
                Utils.showAlert("Error", "Invalid email and / or password", Alert.AlertType.ERROR);
            }
            ;
        } else if (actionEvent.getSource() == btnSignup) {
            Main.setRoot("signup");
        }
    }

    private boolean validateUser(String email, String password) {
        //TODO: uncomment for production
        //return email.equals("admin@tastyfood.com") && password.equals("12345");
        //TODO: for app testing
        return !email.isBlank() && !password.isBlank();
    }
}
