/*
 * Copyright (c) 2016.
 */

package com.crooks.controllers;

import com.crooks.entities.User;
import com.crooks.services.LunchRepository;
import com.crooks.services.UserRepository;
import com.crooks.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by johncrooks on 6/23/16.
 */
@Controller
public class LunchController {

    @Autowired
    UserRepository userRepo;
    @Autowired
    LunchRepository lunchRepo;

    @RequestMapping(path="/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");

        model.addAttribute("username", username);
        //model.addAttribute("lunches", lunchRepo.findAll());

        return "home";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = userRepo.findByName(username);
        if(user==null){
            user = new User(username, PasswordStorage.createHash(password));
            userRepo.save(user);
        }else if(!PasswordStorage.verifyPassword(password, user.getPassword())){
            throw new Exception("Wrong password muh dude!");
        }

        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }




} //End LunchController Class
