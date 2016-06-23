/*
 * Copyright (c) 2016.
 */

package com.crooks.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.List;

/**
 * Created by johncrooks on 6/23/16.
 */
@Entity
@Table
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String password;

    @OneToMany
    List<Lunch> lunchList;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.lunchList = lunchList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Lunch> getLunchList() {
        return lunchList;
    }

    public void setLunchList(List<Lunch> lunchList) {
        this.lunchList = lunchList;
    }
}
