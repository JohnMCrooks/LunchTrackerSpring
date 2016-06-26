/*
 * Copyright (c) 2016.
 */

package com.crooks.entities;

import org.hibernate.action.internal.OrphanRemovalAction;

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
    double price;

    @Column(nullable = false)
    String description;

    @ManyToOne
    User user;

    public Lunch() {
    }

    public Lunch(int id, LocalDate date, String restaurant, double price, String description, User user) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.price = price;
        this.description = description;
        this.user = user;
    }

    public Lunch(LocalDate date, String restaurant, double price, String description, User user) {
        this.date = date;
        this.restaurant = restaurant;
        this.price = price;
        this.description = description;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
