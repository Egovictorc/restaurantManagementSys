package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @CsvBindByName
    private Long id;
    @Column
    @CsvBindByName
    private String patron;
    @Column(name = "title")
    @CsvBindByName
    private String menu;
    @Column
    @CsvBindByName
    private int quantity;
    @Column
    @CsvBindByName
    private double cost;
    @Enumerated(EnumType.STRING)
    @Column
    @CsvBindByName
    private OrderStatus status;
    @Column
    @CsvBindByName
    private String dateOrdered;

    public Order() {
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Order(Long id, String patron, String menu, int quantity, double cost, OrderStatus status, String dateOrdered) {
        this.id = id;
        this.patron = patron;
        this.menu = menu;
        this.quantity = quantity;
        this.cost = cost;
        this.status = status;
        this.dateOrdered = dateOrdered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum OrderStatus {
        DELIVERED, NOT_DELIVERED
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getOrderDate() {
        return dateOrdered;
    }

    public void setOrderDate(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

