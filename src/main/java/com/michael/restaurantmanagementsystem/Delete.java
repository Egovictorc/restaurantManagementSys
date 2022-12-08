package com.michael.restaurantmanagementsystem;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Delete extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        Label email = new Label("Email");
        Label username = new Label("Username");

        email.getStyleClass().add("email");
        username.getStyleClass().add("title");
        // create image
        ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        img.setFitHeight(150);
        img.setFitWidth(200);

        MFXButton mfxButton = new MFXButton();
        // remove default button text
        mfxButton.setText("");
        mfxButton.setGraphic(img);
        mfxButton.setCursor(Cursor.HAND);
        //mfxButton.setContentDisplay(ContentDisplay.TOP);

        root.setSpacing(20);
        root.setStyle("-fx-background-color: #fff;");
        root.getStylesheets().add(String.valueOf(Main.class.getResource("css/style.css")));
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(mfxButton, username, email);

        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
