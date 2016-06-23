/*
 * Copyright (c) 2016.
 */

package com.crooks.entities;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by johncrooks on 6/23/16.
 */

@Entity
@Table(name="lunches")
public class Lunch {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    LocalDate date;

    @Column(nullable = false)
    String restaurant;


    @Column(nullable = false)
    String price;

    @Column(nullable = false)
    String description;

    @ManyToOne
    User user;

    public Lunch() {
    }

    public Lunch(LocalDate date, String restaurant, String price, String description, User user) {
        this.date = date;
        this.restaurant = restaurant;
        this.price = price;
        this.description = description;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
