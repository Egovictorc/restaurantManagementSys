package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;

import java.time.LocalDate;

public class Menu {
    @CsvBindByName
    private int id;

    @CsvBindByName
    private String title;
    @CsvBindByName
    private boolean available;
    @CsvBindByName
    private String imageUrl;
    @CsvBindByName
    private String description;

    private LocalDate createdAt;

    public Menu() {

    }

    public Menu(String title, String description, String imageUrl, boolean available) {
        this.title = title;
        this.available = available;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
