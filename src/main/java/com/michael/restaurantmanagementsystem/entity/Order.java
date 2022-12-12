package com.michael.restaurantmanagementsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String patron;
    @Column(name = "title")
    private String menu;
    @Column
    private int quantity;
    @Column
    private double cost;
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status;
    @Column
    private LocalDate dateOrdered;

    public Order() {
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Order(Long id, String patron, String menu, int quantity, double cost, OrderStatus status, LocalDate dateOrdered) {
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

    public LocalDate getOrderDate() {
        return dateOrdered;
    }

    public void setOrderDate(LocalDate dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

