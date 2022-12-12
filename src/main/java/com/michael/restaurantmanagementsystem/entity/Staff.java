package com.michael.restaurantmanagementsystem.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Entity
@Table(name = "Staff")
public class Staff {

    @CsvBindByName
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long id;

    @CsvBindByName
    @Column
    public String firstName, lastName, email, imageUrl;
    @Column
    private double salary;
    @Column
    @Enumerated(EnumType.STRING)
    public Department dept;


    public Staff() {

    }

    public Staff(Long id, String firstName, String lastName, String email, String imageUrl, double salary, Department dept) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imageUrl = imageUrl;
        this.salary = salary;
        this.dept = dept;
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        InputStream inputStream = Staff.class.getResourceAsStream("/patron.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        List<Staff> personList = new CsvToBeanBuilder<Staff>(bufferedReader)
                .withType(Staff.class).build().parse();

        personList.forEach(System.out::println);

    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", salary=" + salary +
                ", dept=" + dept +
                '}';
    }
}
