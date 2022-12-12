package com.michael.restaurantmanagementsystem.db;

import com.michael.restaurantmanagementsystem.entity.Order;

import java.util.List;

public class DBOrder extends DBUser<Order> {
    @Override
    public List<Order> getAllRecords() {
        List<Order> orderList = session.createQuery("from Order", Order.class).list();
        return orderList;
    }

   /* @Override
    public List<Order> getAllRecords() {
        List<Order> orderList = new ArrayList<>();
        try {
            //conn = DriverManager.getConnection(REMOTE_CONNECTION_URL, REMOTE_USER, REMOTE_PASSWORD);
            conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM order");

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                // Order order = new Order(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("imageUrl"), rs.getDouble("salary"), Staff.Department.valueOf(rs.getString("dept")));
                //orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }*/
}
