package com.michael.restaurantmanagementsystem.config;

import com.michael.restaurantmanagementsystem.entity.Order;
import com.michael.restaurantmanagementsystem.entity.Patron;
import com.michael.restaurantmanagementsystem.entity.Person;
import com.michael.restaurantmanagementsystem.entity.Staff;
import com.opencsv.bean.CsvToBeanBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    public static final Session session = sessionFactory.openSession();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .addAnnotatedClass(com.michael.restaurantmanagementsystem.entity.Staff.class)
                    .addAnnotatedClass(com.michael.restaurantmanagementsystem.entity.Patron.class)
                    .addAnnotatedClass(com.michael.restaurantmanagementsystem.entity.Order.class)
                    .configure("hibernate-h2.cfg.xml")
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    public static void main(String[] args) throws Exception {
        loadData();

        //get staff
        List<Staff> staffList = session.createQuery("from Staff", Staff.class).list();
        System.out.println(staffList);
        /*Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List<Staff> result = session.createQuery( "from Event" ).list();
        List<Staff> staffList = session.createQuery("from Staff", Staff.class).list();
        staffList.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();*/
    }

    public static void loadData() {
        List<Patron> patronList = readPatrons();
        List<Staff> staffList = readStaff();
        List<Order> orderList = readOrders();


        session.beginTransaction();
        orderList.forEach(session::persist);
        patronList.forEach(session::persist);
        staffList.forEach(session::persist);
        //session.persist(staffList.get(0));
        session.getTransaction().commit();

        //personList.forEach(System.out::println);
    }

    private static List<Patron> readPatrons() {
        InputStream inputStream = Person.class.getResourceAsStream("/patron.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Patron> patronList = new CsvToBeanBuilder<Patron>(bufferedReader)
                .withType(Patron.class).build().parse();
        return patronList;
    }

    private static List<Staff> readStaff() {
        InputStream inputStream = Person.class.getResourceAsStream("/staff.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Staff> staffList = new CsvToBeanBuilder<Staff>(bufferedReader)
                .withType(Staff.class).build().parse();
        return staffList;
    }

    private static List<Order> readOrders() {
        InputStream inputStream = Person.class.getResourceAsStream("/orders.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Order> orderList = new CsvToBeanBuilder<Order>(bufferedReader)
                .withType(Order.class).build().parse();
        return orderList;
    }
}