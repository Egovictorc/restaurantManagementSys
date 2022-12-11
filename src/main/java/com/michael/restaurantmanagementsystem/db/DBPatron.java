package com.michael.restaurantmanagementsystem.db;

import com.michael.restaurantmanagementsystem.entity.Patron;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBPatron extends DBUser<Patron> {

    @Override
    public List<Patron> getAllRecords() {
        List<Patron> patronList = new ArrayList<>();
        try {
            //conn = DriverManager.getConnection(REMOTE_CONNECTION_URL, REMOTE_USER, REMOTE_PASSWORD);
            conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM patrons");

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Patron patron = new Patron(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("email"), rs.getString("imageUrl"), Patron.PatronLevel.valueOf(rs.getString("level")));
                patronList.add(patron);
            }
            System.out.println(rs.getFetchSize());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patronList;
    }

    public static void main(String[] args) {
        DBPatron dbPatron = new DBPatron();
        System.out.println(dbPatron.getAllRecords());
    }
}
