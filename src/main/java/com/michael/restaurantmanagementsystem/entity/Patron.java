package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Patron extends User {
    @CsvBindByName
    private PatronLevel level;

    public Patron() {
    }

    enum PatronLevel {
        DIAMOND, GOLD, SILVER
    }

    public PatronLevel getLevel() {
        return level;
    }

    public void setLevel(PatronLevel level) {
        this.level = level;
    }

/*
    @Override
    public Node createView() {
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
        mfxButton.setOnMouseClicked(event -> {

        });
        //mfxButton.setContentDisplay(ContentDisplay.TOP);

        root.setSpacing(20);
        root.setStyle("-fx-background-color: #fff; -fx-padding: 20px;");
        root.getStylesheets().add(String.valueOf(Main.class.getResource("css/style.css")));
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(mfxButton, username, email);
        return root;
    }
*/

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "level=" + level +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static void main(String[] args) {

        InputStream inputStream = Patron.class.getResourceAsStream("/PATRON_DATA.csv");
        List<Patron> patronList;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            patronList = new CsvToBeanBuilder<Patron>(bufferedReader)
                    .withType(Patron.class).build().parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        patronList.forEach(System.out::println);

        //Patron patron = new Patron(1, "uche", "okonkwo", "uche@gmail.com", "imageUrl");
        //System.out.println(patron);
    }
}
