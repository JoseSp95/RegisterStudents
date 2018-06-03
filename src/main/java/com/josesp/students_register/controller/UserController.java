package com.josesp.students_register.controller;

import com.josesp.students_register.model.User;
import com.josesp.students_register.paginator.PageRender;
import com.josesp.students_register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.Date;

@Controller
@SessionAttributes(value = "user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showList(@RequestParam(name = "page", defaultValue = "1") int page, Model model){
        if (page > 0) {
            page -= 1;
        }
        Pageable pageable = PageRequest.of(page,5);
        Page<User> users = this.userService.findAll(pageable);
        PageRender<User> linkPages = new PageRender<>(users, "/users");
        model.addAttribute("page", linkPages);
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

    @RequestMapping(value = "/users/create", method = RequestMethod.GET)
    public String formUser(Model model){
        model.addAttribute("title", "Create User");
        model.addAttribute("user", new User());
        return "form";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String formUser(@PathVariable(value = "id") String id, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("title", "Update User");
        Long idLong;
        try{
            idLong = Long.parseLong(id);
        }catch (Exception e){
            return "redirect:users";
        }

        User user = this.userService.findById(idLong);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User does not exist");
            return "redirect:users";
        }

        model.addAttribute("user",user);

        return "form";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String createOrUpdate(@Valid User user, BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes, SessionStatus status){

        if (bindingResult.hasErrors()) {
            // System.out.println(bindingResult.toString());
            return "form";
        }

        if (user.getId() == null) {
            user.setCreatedAt(new Date());
            redirectAttributes.addFlashAttribute("success", "User created successfully");
        } else {
            redirectAttributes.addFlashAttribute("success", "user updated successfully");
        }

        this.userService.save(user);
        status.setComplete();
        System.out.println(user.toString());
        return "redirect:/users";
    }

}
