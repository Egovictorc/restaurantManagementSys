package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Staff {
    @CsvBindByName
    public int id;
    @CsvBindByName
    public String firstName, lastName, email, gender, imageUrl;
    private double salary;
    public Department dept;

    public Staff() {

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Staff(int id, String firstName, String lastName, String email, String gender, String imageUrl) {
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

    public enum Department {
        ACCOUNTING, ENGINEERING, ADMINISTRATION
    }

    public static void main(String[] args) {
        InputStream inputStream = Staff.class.getResourceAsStream("/PATRON_DATA.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Staff> personList = new CsvToBeanBuilder<Staff>(bufferedReader)
                .withType(Staff.class).build().parse();

        personList.forEach(System.out::println);

    }
}
