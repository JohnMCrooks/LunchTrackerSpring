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
@Table (name="users")
public class User {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy="user", orphanRemoval = true)
    List<Lunch> lunchList;

    Boolean openEdit;
    Boolean isMe;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.openEdit = false;
        this.isMe = false;

    }

    public User(String name, String password, List<Lunch> lunchList, Boolean openEdit) {
        this.name = name;
        this.password = password;
        this.lunchList = lunchList;
        this.openEdit = openEdit;

    }

    public Boolean getMe() {
        return isMe;
    }

    public void setMe(Boolean me) {
        isMe = me;
    }

    public Boolean getOpenEdit() {
        return openEdit;
    }

    public void setOpenEdit(Boolean openEdit) {
        this.openEdit = openEdit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
