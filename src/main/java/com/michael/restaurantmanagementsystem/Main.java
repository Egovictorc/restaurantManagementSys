package com.michael.restaurantmanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    //Overview: Entry point of JavaFx application
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // MODIFIES: stage
        // EFFECTS: starts the javaFx application instance
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
        scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Tasty Food Restaurant");

        // set background Image
   /*     Image img = new Image(String.valueOf(Main.class.getResource("images/doctor.png")));
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        scene.getRoot().setBackground(bGround);*/
        // scene.getStylesheets().add(String.valueOf(Main.class.getResource("css/style.css")));

        // set stage icon
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("images/icons8_food_72px.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        // EFFECTS: sets the root node of the scene
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // EFFECTS: loads the specified fxml file, throws IOException if fxml file is not found
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}