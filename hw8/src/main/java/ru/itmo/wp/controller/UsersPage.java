package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String setDisabled(@RequestParam long id, Model model, HttpSession httpSession) {
        User user = userService.findById(id);
        if (user == null) {
            setMessage(httpSession, "Invalid User id");
            return "UsersPage";
        }
        userService.setDisabled(id, !user.isDisabled());
//        model.addAttribute("users", userService.findAll());
        setMessage(httpSession, "User " + id + " modified");
        return "redirect:/";
    }
}
