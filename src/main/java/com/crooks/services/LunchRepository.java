/*
 * Copyright (c) 2016.
 */

package com.crooks.services;

import com.crooks.entities.Lunch;
import com.crooks.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 6/23/16.
 */
public interface LunchRepository extends CrudRepository<Lunch, Integer> {

    @Query("SELECT SUM(price) FROM Lunch l  WHERE  l.user.id = ?1")  //use class name for 'Lunch' specification. % sign is a wildcard
    public Iterable<Lunch> usertotal(int userId);

    @Query("SELECT AVG(price)  FROM Lunch l  WHERE  l.user.id = ?1")  //use class name for 'Lunch' specification. % sign is a wildcard
    public Iterable<Lunch> userAverage(int userId);

    @Query("SELECT SUM(price) FROM Lunch l")
    public Iterable<Lunch> globalTotal();

    @Query("SELECT AVG(price) FROM Lunch l")
    public Iterable<Lunch> globalAvg();
}
