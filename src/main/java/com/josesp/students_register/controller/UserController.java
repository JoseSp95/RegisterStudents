package com.josesp.students_register.controller;

import com.josesp.students_register.model.User;
import com.josesp.students_register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showList(Model model){
        Pageable pageable = PageRequest.of(0,10);
        Page<User> users = this.userService.findAll(pageable);
        model.addAttribute("users", users);
        return "list";
    }

    @RequestMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        if (id != null && id >= 0){
            this.userService.deleteById(id);
        }
        return "redirect:/users";
    }

}
