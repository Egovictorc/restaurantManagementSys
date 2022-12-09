package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Person {
    @CsvBindByName
    public int id;
    @CsvBindByName
    public String firstName, lastName, email, gender, imageUrl;

    public Person() {

    }

    public Person(int id, String firstName, String lastName, String email, String gender, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

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

    public static void main(String[] args) {
        InputStream inputStream = Person.class.getResourceAsStream("/PATRON_DATA.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Person> personList = new CsvToBeanBuilder<Person>(bufferedReader)
                .withType(Person.class).build().parse();

        personList.forEach(System.out::println);

    }
}
