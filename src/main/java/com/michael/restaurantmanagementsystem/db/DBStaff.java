package com.michael.restaurantmanagementsystem.db;

import com.michael.restaurantmanagementsystem.config.HibernateUtil;
import com.michael.restaurantmanagementsystem.entity.Staff;

import java.util.List;

public class DBStaff extends DBUser<Staff> {

    @Override
    public List<Staff> getAllRecords() {
        HibernateUtil.loadData();
        List<Staff> staffList = session.createQuery("from Staff", Staff.class).list();
        return staffList;
    }

    public static void main(String[] args) {
        DBStaff staff = new DBStaff();
        System.out.println(staff.getAllRecords());
    }
    /*  @Override
  public List<Staff> getAllRecords() {
        List<Staff> staffList = new ArrayList<>();
        try {
            //conn = DriverManager.getConnection(REMOTE_CONNECTION_URL, REMOTE_USER, REMOTE_PASSWORD);
            conn = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM staff");

            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Staff staff = new Staff(rs.getLong("id"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("imageUrl"), rs.getDouble("salary"), Staff.Department.valueOf(rs.getString("dept")));
                staffList.add(staff);
            }
            rs.close();
            psmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return staffList;
    }*/
}
