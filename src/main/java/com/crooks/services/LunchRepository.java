/*
 * Copyright (c) 2016.
 */

package com.crooks.services;

import com.crooks.entities.Lunch;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by johncrooks on 6/23/16.
 */
public interface LunchRepository extends CrudRepository<Lunch, Integer> {
}
