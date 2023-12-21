package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.UserCredentialsRegister;
import ru.itmo.wp.form.validator.UserCredentialsEnterValidator;
import ru.itmo.wp.form.validator.UserCredentialsRegisterValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1")
public class JwtController {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserCredentialsEnterValidator userCredentialsEnterValidator;
    private final UserCredentialsRegisterValidator userCredentialsRegisterValidator;

    public JwtController(JwtService jwtService, UserService userService, UserCredentialsEnterValidator userCredentialsEnterValidator, UserCredentialsRegisterValidator userCredentialsRegisterValidator) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userCredentialsEnterValidator = userCredentialsEnterValidator;
        this.userCredentialsRegisterValidator = userCredentialsRegisterValidator;
    }

    @InitBinder("userCredentialsRegister")
    public void initRegisterBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegisterValidator);
    }
    @InitBinder("userCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsEnterValidator);
    }

    @PostMapping("jwt")
    public String create(@RequestBody @Valid UserCredentials userCredentials, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        User user = userService.findByLoginAndPassword(userCredentials.getLogin(), userCredentials.getPassword());
        return jwtService.create(user);
    }

    @PostMapping("register")
    public String register(@Valid @RequestBody UserCredentialsRegister userCredential,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return jwtService.create(userService.register(userCredential));
    }
}
