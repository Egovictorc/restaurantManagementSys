package com.michael.restaurantmanagementsystem.entity;

import java.time.LocalDate;

public class Order {
    private int id;
    private Patron patron;
    private Product product;
    private LocalDate orderDate;
    private int quantity;

    public Order(int id, Patron patron, Product product, LocalDate orderDate, int quantity) {
        this.id = id;
        this.patron = patron;
        this.product = product;
        this.orderDate = orderDate;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

