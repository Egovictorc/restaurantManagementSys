package com.michael.restaurantmanagementsystem.config;

import com.michael.restaurantmanagementsystem.entity.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration()
                    .addAnnotatedClass(com.michael.restaurantmanagementsystem.entity.Staff.class)
                    .addAnnotatedClass(com.michael.restaurantmanagementsystem.entity.Patron.class)
                    .addAnnotatedClass(com.michael.restaurantmanagementsystem.entity.Order.class)
                    //.configure()
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

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    public static void main(String[] args) throws Exception {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List<Staff> result = session.createQuery( "from Event" ).list();
        List<Staff> staffList = session.createQuery("from Staff", Staff.class).list();
        staffList.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();


    }
}