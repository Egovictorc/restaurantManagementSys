package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;

public abstract class User {

    @CsvBindByName
    public Long id;
    @CsvBindByName
    public String firstName, lastName, gender, email, imageUrl;


    public User() {

    }

    public User(Long id, String firstName, String lastName, String gender, String email, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public abstract void setId(Long id);

    public abstract String getFirstName();

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //public abstract Node createView();
}
