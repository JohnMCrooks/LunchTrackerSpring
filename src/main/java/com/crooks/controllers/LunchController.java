/*
 * Copyright (c) 2016.
 */

package com.crooks.controllers;

import com.crooks.entities.Lunch;
import com.crooks.entities.User;
import com.crooks.services.LunchRepository;
import com.crooks.services.UserRepository;
import com.crooks.utilities.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        User user = userRepo.findByName(username);

        model.addAttribute("username", username);
        model.addAttribute("lunches", lunchRepo.findAll());
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("globalTotal", lunchRepo.globalTotal());
        model.addAttribute("globalAvg", lunchRepo.globalAvg());
        if (user !=null) {
            model.addAttribute("openEdit", user.getOpenEdit());
            model.addAttribute("isMe", user.getMe());
            model.addAttribute("userTotal", lunchRepo.usertotal(user.getId()));
            model.addAttribute("userAvg", lunchRepo.userAverage(user.getId()));

        }
        return "home";
    }

    @RequestMapping(path="/login", method = RequestMethod.POST)
    public String login(HttpSession session, String username, String password) throws Exception {
        User user = userRepo.findByName(username);
        if(user==null){
            user = new User(username, PasswordStorage.createHash(password));
            user.setMe(true);
            userRepo.save(user);

        }else if(!PasswordStorage.verifyPassword(password, user.getPassword())){
            throw new Exception("Wrong password muh dude!");
        }
        user.setMe(true);
        userRepo.save(user);

        session.setAttribute("username", username);
        return "redirect:/";
    }

    @RequestMapping(path="/add-lunch", method = RequestMethod.POST)
    public String addLunch(HttpSession session, String date, String restaurant, String description, double price){
      String username = (String) session.getAttribute("username");
        User user = userRepo.findByName(username);
        if (user == null){
            return "redirect:/";
        }
        Lunch lunch = new Lunch(LocalDate.parse(date),restaurant, price,description, user);
        List<Lunch> tempLunch = user.getLunchList();
        tempLunch.add(lunch);
        user.setLunchList(tempLunch);
        user.setOpenEdit(false);
        lunchRepo.save(lunch);


        return "redirect:/";

    }
    @RequestMapping(path="/edit-lunch", method = RequestMethod.POST)
    public String editLunch(HttpSession session, int id, String date, String restaurant, String description, double price){
        String username = (String) session.getAttribute("username");
        User user = userRepo.findByName(username);
        if(username==null){
            return "redirect:/";
        }else if( username.equals(user.getName())){
            Lunch lunch = new Lunch(id, LocalDate.parse(date), restaurant, price, description, user);
            lunchRepo.save(lunch);
            user.setOpenEdit(false);
            userRepo.save(user);
        }
        return "redirect:/";

    }

    @RequestMapping(value="/openEdit", method = RequestMethod.POST)
    public String openEdit(HttpSession session, String name) throws Exception {
        String username = (String) session.getAttribute("username");
        User user1 = userRepo.findByName(username);
        if(user1 ==null){
            throw new Exception("---- You must Log in if you want to edit or delete a post! ----");
        } else if (name.equals(user1.getName())){
            if(user1.getOpenEdit()){
                user1.setOpenEdit(!user1.getOpenEdit());
                userRepo.save(user1);
            }else if (!user1.getOpenEdit()){
                user1.setOpenEdit(!user1.getOpenEdit());
                userRepo.save(user1);
            }
        } else{
            throw new Exception(" This isn't your post, You can't edit this... ");
        }

            return "redirect:/";
    }

    @RequestMapping(path="/delete-lunch", method=RequestMethod.POST)
    public String deleteLunch(HttpSession session, int id) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = userRepo.findByName(username);
            lunchRepo.delete(id);
            System.out.println("blahhhh");

        return "redirect:/";
    }

    @RequestMapping(path="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        String username = (String) session.getAttribute("username");
        User user = userRepo.findByName(username);
        user.setMe(false);                              //flipping the boolean so the edit/delete buttons won't show for anyone else.
        userRepo.save(user);
        session.invalidate();
        return "redirect:/";
    }




} //End LunchController Class
