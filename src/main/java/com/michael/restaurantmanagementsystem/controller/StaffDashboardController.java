package com.michael.restaurantmanagementsystem.controller;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.entity.Menu;
import com.michael.restaurantmanagementsystem.entity.ModalType;
import com.michael.restaurantmanagementsystem.entity.Patron;
import com.michael.restaurantmanagementsystem.service.MenuService;
import com.michael.restaurantmanagementsystem.service.PatronService;
import com.opencsv.bean.CsvToBeanBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StaffDashboardController implements Initializable {
    PatronService patronService = new PatronService();
    MenuService menuService = new MenuService();

    @FXML
    private AnchorPane root;
    @FXML
    private VBox pnItems = null;

    @FXML
    private FlowPane menuItemsPane = null;
    @FXML
    private TilePane patronsPane = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set overview panel to front
        pnlOverview.toFront();

        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getResource("/com/michael/restaurantmanagementsystem/fxml/item.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #CECECE");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #FFF");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {

        Button source = (Button) actionEvent.getSource();
        List<Button> btnList = List.of(btnCustomers, btnMenus, btnOrders, btnOverview, btnPackages, btnSettings);
        //remove active style class from other buttons
        btnList.forEach(btn -> btn.getStyleClass().remove("active-button"));
        //add active style class to action source
        source.getStyleClass().add("active-button");

        if (source == btnCustomers) {
            //pnlCustomer.setStyle("-fx-background-color : #1620A1");
            List<Patron> patronList = getAllPatrons();
            List<Node> nodes = patronList.stream().map(patronService::createView).toList();
            //List<Node> nodes = patronList.stream().map(Patron::createView).toList();

            patronsPane.getChildren().addAll(nodes);
            pnlCustomer.toFront();
        }
        /*if (actionEvent.getSource() == btnCustomers) {
            //pnlCustomer.setStyle("-fx-background-color : #1620A1");
            Node[] nodes = new Node[10];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = FXMLLoader.load(getClass().getResource("/com/michael/restaurantmanagementsystem/fxml/patron.fxml"));
                //nodes[i].getC
            }
            patronsPane.getChildren().addAll(nodes);
            pnlCustomer.toFront();
        }*/
        if (source == btnMenus) {
            //pnlMenus.setStyle("-fx-background-color : #53639F");
            /*Node[] nodes = new Node[10];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = FXMLLoader.load(getClass().getResource("/com/michael/restaurantmanagementsystem/fxml/menu.fxml"));
            }*/

            List<Menu> menuList = getAllMenu();
            List<Node> nodes = menuList.stream().map(menuService::createView).toList();
            //List<Node> nodes = patronList.stream().map(Patron::createView).toList();

            menuItemsPane.getChildren().removeAll();
            menuItemsPane.getChildren().addAll(nodes);
            menuItemsPane.setPrefWrapLength(pnlMenus.getMaxWidth());
            pnlMenus.toFront();
        }
        if (source == btnOverview) {
            //pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.setStyle("-fx-background-color : #FFF");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if (actionEvent.getSource() == btnSignout) {
            System.out.println("clicked on signout button");
            //Main.setRoot("login");
            Main.setRoot("login", new Rectangle2D(0, 0, 800.0, 600.0));
        }
    }

    public List<Patron> getAllPatrons() {
        InputStream inputStream = Patron.class.getResourceAsStream("/PATRON_DATA.csv");
        List<Patron> patronList;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            patronList = new CsvToBeanBuilder<Patron>(bufferedReader)
                    .withType(Patron.class).build().parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return patronList;
    }

    public List<Menu> getAllMenu() {
        InputStream inputStream = Menu.class.getResourceAsStream("/MENU_DATA.csv");
        List<Menu> menuList;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            menuList = new CsvToBeanBuilder<Menu>(bufferedReader)
                    .withType(Menu.class).build().parse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return menuList;
    }

    public void handleAddPatron(ActionEvent actionEvent) {
        patronService.handleClick(actionEvent, ModalType.NEW);
    }

    public void handleAddMenu(ActionEvent actionEvent) {

        menuService.handleClick(actionEvent, ModalType.NEW);
    }
}

