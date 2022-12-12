package com.michael.restaurantmanagementsystem.controller;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.db.DBOrder;
import com.michael.restaurantmanagementsystem.entity.Order;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.LongFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.utils.others.observables.When;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class PatronDashboardController implements Initializable {
    DBOrder dbOrder = new DBOrder();
    @FXML
    private AnchorPane root;
    @FXML
    private VBox pnItems = null;

    @FXML
    private FlowPane menuItemsPane = null;
    @FXML
    private Button btnProfile;

    @FXML
    private Button btnOrders;

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
    private Pane pnlOrders, pnlProfile;

    @FXML
    private Pane pnlMenus;
    @FXML
    private MFXPaginatedTableView<Order> orderPaginatedTableview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set app to use device size
        setupOrderPaginatedTableview();
        orderPaginatedTableview.autosizeColumnsOnInitialization();
        When.onChanged(orderPaginatedTableview.currentPageProperty())
                .then((oldValue, newValue) -> orderPaginatedTableview.autosizeColumns())
                .listen();
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {

        if (actionEvent.getSource() == btnMenus) {
            //pnlMenus.setStyle("-fx-background-color : #53639F");
            Node[] nodes = new Node[10];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = FXMLLoader.load(getClass().getResource("/com/michael/restaurantmanagementsystem/fxml/menu.fxml"));
            }
            menuItemsPane.getChildren().addAll(nodes);
            menuItemsPane.setPrefWrapLength(pnlMenus.getMaxWidth());
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnProfile) {
            //pnlOverview.setStyle("-fx-background-color : #02030A");
            //pnlProfile.setStyle("-fx-background-color : #FFF");
            pnlProfile.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            //pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
        if (actionEvent.getSource() == btnSignout) {
            System.out.println("clicked on signout button");
            //Main.setRoot("login");
            Main.setRoot("login", new Rectangle2D(0, 0, 800.0, 600.0));
        }
    }

    private void setupOrderPaginatedTableview() {
        MFXTableColumn<Order> idColumn = new MFXTableColumn<>("ID", false, Comparator.comparing(Order::getId));
        MFXTableColumn<Order> titleColumn = new MFXTableColumn<>("Order Title", false, Comparator.comparing(Order::getMenu));
        MFXTableColumn<Order> patronColumn = new MFXTableColumn<>("Patron", false, Comparator.comparing(Order::getPatron));
        MFXTableColumn<Order> quantityColumn = new MFXTableColumn<>("Quantity", false, Comparator.comparing(Order::getQuantity));
        MFXTableColumn<Order> costColumn = new MFXTableColumn<>("Cost", false, Comparator.comparing(Order::getCost));
        MFXTableColumn<Order> dateOrderedColumn = new MFXTableColumn<>("Date ordered", false, Comparator.comparing(Order::getOrderDate));
        MFXTableColumn<Order> statusColumn = new MFXTableColumn<>("Status", false, Comparator.comparing(Order::getStatus));

        idColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getId));
        titleColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getMenu));
        patronColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getPatron));
        quantityColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getQuantity));
        costColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getCost) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        dateOrderedColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getOrderDate));
        statusColumn.setRowCellFactory(order -> new MFXTableRowCell<>(Order::getStatus));
        titleColumn.setAlignment(Pos.CENTER_RIGHT);

        orderPaginatedTableview.getTableColumns().addAll(idColumn, titleColumn, patronColumn, quantityColumn, costColumn, dateOrderedColumn, statusColumn);
        orderPaginatedTableview.getFilters().addAll(
                new LongFilter<>("ID", Order::getId),
                new StringFilter<>("Order Title", Order::getMenu),
                new StringFilter<>("Patron", Order::getPatron),
                new DoubleFilter<>("Cost", Order::getCost),
                new IntegerFilter<>("Quantity", Order::getQuantity)
                // new EnumFilter<Order, Order.Department>("Dept", Order::getDept),
                //new StringFilter<>("Status", Order::getStatus);
                //new EnumFilter<>("Status", Order::getStatus)
                //new StringFilter<>("State", Staff::getState, Staff.Level.class),
        );

        ObservableList<Order> observableList = FXCollections.observableArrayList(dbOrder.getAllRecords());
        orderPaginatedTableview.setItems(observableList);
    }
}

