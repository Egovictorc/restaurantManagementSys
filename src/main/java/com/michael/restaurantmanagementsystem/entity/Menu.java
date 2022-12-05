package com.michael.restaurantmanagementsystem.entity;

public class Menu {
    private String title;
    private String status;
    private String image;
    private String description;

    public Menu(String title, String status, String image, String description) {
        this.title = title;
        this.status = status;
        this.image = image;
        this.description = description;
    }
}
