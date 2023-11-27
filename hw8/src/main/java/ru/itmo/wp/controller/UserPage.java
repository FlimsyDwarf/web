package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/{id}", "/user/"})
    public String userGet(Model model, @PathVariable(required = false) String id) {
        User user = null;
        try {
            user = userService.findById(Long.valueOf(id));
        } catch (NumberFormatException ignored) {
        }
        model.addAttribute("user", user);
        return "UserPage";
    }
}
