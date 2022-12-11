package com.michael.restaurantmanagementsystem.service;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.entity.Menu;
import com.michael.restaurantmanagementsystem.entity.ModalType;
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

public class MenuService implements RestaurantService<Menu> {
    @Override
    public Node createView(Menu menu) {
        VBox root = new VBox();
        Label title = new Label(menu.getTitle());
        Label description = new Label(menu.getDescription());

        title.getStyleClass().add("title");
        // create image
        //ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        ImageView img = new ImageView(new Image(String.valueOf(Main.class.getResource(menu.getImageUrl()))));
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
        root.setPrefWidth(250);
        root.setMaxWidth(root.getPrefWidth());
        root.setStyle("-fx-background-color: #fff; -fx-padding: 20px;");
        root.getStylesheets().add(String.valueOf(Main.class.getResource("css/style.css")));
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(mfxButton, title, description);
        return root;
    }


    public void handleClick(ActionEvent actionEvent, ModalType modalType) {
        Scene scene = null;
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(PatronService.class.getResource("fxml/signup.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/modal/" + modalType.toString().toLowerCase() + "-menu.fxml"));
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage dialog = new Stage();
        // populate dialog with controls.
        Stage parentStage = (Stage) ((Node) (actionEvent.getSource())).getScene().getWindow();
        dialog.initOwner(parentStage);
        dialog.setScene(scene);
        dialog.setTitle(modalType == ModalType.UPDATE ? "Update Menu" : "Add Menu");
        dialog.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setResizable(false);
        dialog.showAndWait();
    }
}
