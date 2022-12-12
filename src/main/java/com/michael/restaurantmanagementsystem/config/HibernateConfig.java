package com.michael.restaurantmanagementsystem.config;

import com.michael.restaurantmanagementsystem.entity.Staff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HibernateConfig {
    static SessionFactory sessionFactory;

    protected static void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }


    }

    public static void main(String[] args) throws Exception {
        setUp();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //List<Staff> result = session.createQuery( "from Event" ).list();
        List<Staff> staffList = session.createQuery("from Staff", Staff.class).list();
        staffList.forEach(System.out::println);
        session.getTransaction().commit();
        session.close();


    }


}
