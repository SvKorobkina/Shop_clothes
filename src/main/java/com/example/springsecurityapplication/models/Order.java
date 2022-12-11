package com.example.springsecurityapplication.models;

import com.example.springsecurityapplication.enumm.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    private int count;

    private float price;

    private LocalDateTime dateTime;

    private Status status;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Person person;

//    public Order(String uuid, int count, float price, Status[] values, Product product, Person person) {
//    }

    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Order(String number, int count, float price, Status status, Product product, Person person) {
        this.number = number;
        this.count = count;
        this.price = price;
        this.status = status;
        this.product = product;
        this.person = person;
    }

    public Order(){

    }


}
