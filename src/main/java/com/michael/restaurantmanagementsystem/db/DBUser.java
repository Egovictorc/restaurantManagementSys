package com.michael.restaurantmanagementsystem.db;

import java.sql.*;
import java.util.List;

public abstract class DBUser<T> {
    static Connection conn = null;
    static final String DATABASE = "rms",
            CONNECTION_URL = "jdbc:mysql://localhost/" + DATABASE + "?createDatabaseIfNotExist=true",
            USER = "root",
            PASSWORD = "1234";
    static final String REMOTE_DATABASE = "",
            REMOTE_CONNECTION_URL = "jdbc:mysql://localhost/" + REMOTE_DATABASE + "?createDatabaseIfNotExist=true",
            REMOTE_USER = "root",
            REMOTE_PASSWORD = "1234";

    public static void main(String[] args) {
        System.out.println("URL " + CONNECTION_URL);
        try {
            //conn = DriverManager.getConnection(REMOTE_CONNECTION_URL, REMOTE_USER, REMOTE_PASSWORD);
            conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM patrons");

            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("firstName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract List<T> getAllRecords();
}
