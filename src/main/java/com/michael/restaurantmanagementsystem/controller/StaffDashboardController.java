package com.michael.restaurantmanagementsystem.controller;

import com.michael.restaurantmanagementsystem.Main;
import com.michael.restaurantmanagementsystem.db.DBPatron;
import com.michael.restaurantmanagementsystem.db.DBStaff;
import com.michael.restaurantmanagementsystem.entity.Menu;
import com.michael.restaurantmanagementsystem.entity.ModalType;
import com.michael.restaurantmanagementsystem.entity.Patron;
import com.michael.restaurantmanagementsystem.entity.Staff;
import com.michael.restaurantmanagementsystem.service.MenuService;
import com.michael.restaurantmanagementsystem.service.PatronService;
import com.opencsv.bean.CsvToBeanBuilder;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXPaginatedTableView;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
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
import javafx.scene.layout.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class StaffDashboardController implements Initializable {
    PatronService patronService = new PatronService();
    MenuService menuService = new MenuService();
    DBPatron dbPatron = new DBPatron();
    DBStaff dbStaff = new DBStaff();
    @FXML
    private AnchorPane root;
    @FXML
    private VBox pnItems = null;

    @FXML
    private FlowPane menuItemsPane = null;
    @FXML
    private TilePane patronsPane = null;

    @FXML
    private Button btnOrders, btnOverview, btnMenus, btnCustomers, btnPackages, btnSignout, btnSettings, btnStaffManagement;

    @FXML
    private Pane pnlCustomer, pnlOrders, pnlMenus, pnlOverview, pnlStaffManagement;

    @FXML
    private MFXPaginatedTableView<Staff> paginated;

    @FXML
    private MFXTextField txtFirstName, txtLastName, txtEmail, txtSalary, imgUrl;

    @FXML
    MFXComboBox<Staff.Department> txtDept;
    ObservableList<Staff.Department> departments = FXCollections.observableArrayList(
            Staff.Department.ACCOUNTING, Staff.Department.ENGINEERING, Staff.Department.ADMINISTRATION
    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtDept.setItems(departments);
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

        setupPaginated();
        paginated.autosizeColumnsOnInitialization();
        When.onChanged(paginated.currentPageProperty())
                .then((oldValue, newValue) -> paginated.autosizeColumns())
                .listen();

    }

    public void handleClicks(ActionEvent actionEvent) throws IOException {

        Button source = (Button) actionEvent.getSource();
        List<Button> btnList = List.of(btnCustomers, btnMenus, btnOrders, btnOverview, btnPackages, btnSettings, btnStaffManagement);
        //remove active style class from other buttons
        btnList.forEach(btn -> btn.getStyleClass().remove("active-button"));
        //add active style class to action source
        source.getStyleClass().add("active-button");

        if (source == btnCustomers) {
            //pnlCustomer.setStyle("-fx-background-color : #1620A1");
            //List<Patron> patronList = getAllPatrons();
            List<Patron> patronList = dbPatron.getAllRecords();
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
        if (actionEvent.getSource() == btnStaffManagement) {
            //pnlStaffManagement.setStyle("-fx-background-color : #464F67");
            pnlStaffManagement.toFront();
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


    private void setupPaginated() {
        MFXTableColumn<Staff> idColumn = new MFXTableColumn<>("ID", false, Comparator.comparing(Staff::getId));
        MFXTableColumn<Staff> firstNameColumn = new MFXTableColumn<>("First Name", false, Comparator.comparing(Staff::getFirstName));
        MFXTableColumn<Staff> lastNameColumn = new MFXTableColumn<>("Last Name", false, Comparator.comparing(Staff::getLastName));
        MFXTableColumn<Staff> deptColumn = new MFXTableColumn<>("Department", false, Comparator.comparing(Staff::getDept));
        MFXTableColumn<Staff> emailColumn = new MFXTableColumn<>("Email", false, Comparator.comparing(Staff::getEmail));
        MFXTableColumn<Staff> imgUrlColumn = new MFXTableColumn<>("Image Path", false, Comparator.comparing(Staff::getImageUrl));
        MFXTableColumn<Staff> salaryColumn = new MFXTableColumn<>("Salary", false, Comparator.comparing(Staff::getSalary));

        idColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getId));
        firstNameColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getFirstName));
        lastNameColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getLastName) {{
            setAlignment(Pos.CENTER_RIGHT);
        }});
        emailColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getEmail));
        deptColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getDept));
        salaryColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getSalary));
        imgUrlColumn.setRowCellFactory(staff -> new MFXTableRowCell<>(Staff::getImageUrl));
        firstNameColumn.setAlignment(Pos.CENTER_RIGHT);

        paginated.getTableColumns().addAll(idColumn, firstNameColumn, lastNameColumn, emailColumn, deptColumn, salaryColumn, imgUrlColumn);
        paginated.getFilters().addAll(
                new IntegerFilter<>("ID", Staff::getId),
                new StringFilter<>("First Name", Staff::getFirstName),
                new StringFilter<>("Last Name", Staff::getLastName),
                new DoubleFilter<>("Salary", Staff::getSalary),
                new StringFilter<>("Image url", Staff::getImageUrl),
                // new EnumFilter<Staff, Staff.Department>("Dept", Staff::getDept),
                new StringFilter<>("Email", Staff::getEmail)
                //new StringFilter<>("State", Staff::getState, Staff.Level.class),
        );

        ObservableList<Staff> observableList = FXCollections.observableArrayList(dbStaff.getAllRecords());
        paginated.setItems(observableList);
    }
}

