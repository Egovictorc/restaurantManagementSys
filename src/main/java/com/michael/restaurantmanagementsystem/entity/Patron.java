package com.michael.restaurantmanagementsystem.entity;

import com.michael.restaurantmanagementsystem.Main;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Patron extends User {

    private PatronLevel level;

    enum PatronLevel {
        DIAMOND, GOLD, SILVER
    }


    public PatronLevel getLevel() {
        return level;
    }


    public void setLevel(PatronLevel level) {
        this.level = level;
    }

    @Override
    public Parent createView() {
        VBox root = new VBox();
        Label email = new Label(getEmail());
        Label username = new Label(getFirstName().concat(" ").concat(getLastName()));

        email.getStyleClass().add("email");
        username.getStyleClass().add("title");
        // create image
        //ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource(getImageUrl()))));
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
        return root;
    }
}
