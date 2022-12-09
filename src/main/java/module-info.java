module com.michael.restaurantmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;

    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;

    requires java.sql;
    requires com.opencsv;
    exports com.michael.restaurantmanagementsystem.entity to com.opencsv;

    opens com.michael.restaurantmanagementsystem to javafx.fxml;
    exports com.michael.restaurantmanagementsystem;

    opens com.michael.restaurantmanagementsystem.controller to javafx.fxml;
    exports com.michael.restaurantmanagementsystem.controller;
}