package com.michael.restaurantmanagementsystem.service;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.entity.ModalType;
import com.michael.restaurantmanagementsystem.entity.Patron;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PatronService implements RestaurantService<Patron> {
    @Override
    public Node createView(Patron patron) {
        VBox root = new VBox();
        Label email = new Label(patron.getEmail());
        Label username = new Label(patron.getFirstName().concat(" ").concat(patron.getLastName()));

        email.getStyleClass().add("email");
        username.getStyleClass().add("title");
        // create image
        //ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource(patron.getImageUrl()))));
        img.setFitHeight(150);
        img.setFitWidth(200);

        MFXButton mfxButton = new MFXButton();
        // remove default button text
        mfxButton.setText("");
        mfxButton.setGraphic(img);
        mfxButton.setCursor(Cursor.cursor("HAND"));

        //add action event handler
        mfxButton.setOnAction(event -> handleClick(event, ModalType.UPDATE));

        root.setSpacing(20);
        root.setStyle("-fx-background-color: #fff; -fx-padding: 20px;");
        root.getStylesheets().add(String.valueOf(Main.class.getResource("css/style.css")));
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(mfxButton, username, email);
        return root;
    }


    public void handleClick(ActionEvent actionEvent, ModalType modalType) {
        Scene scene = null;
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(PatronService.class.getResource("fxml/signup.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/modal/" + modalType.toString().toLowerCase() + "-patron.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage dialog = new Stage();
        // populate dialog with controls.
        Stage parentStage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        dialog.initOwner(parentStage);
        dialog.setScene(scene);
        dialog.setTitle(modalType == ModalType.UPDATE ? "Update Patron" : "Add Patron");
        dialog.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.showAndWait();
    }
}
