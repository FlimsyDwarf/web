package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserCredentialsRegister;
import ru.itmo.wp.service.UserService;

@Component
public class UserCredentialsRegisterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentialsRegister.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentialsRegister userCredentials = (UserCredentialsRegister) target;
            if (userCredentials.getLogin().length() < 3 || userCredentials.getLogin().length() > 16) {
                errors.rejectValue("login", "login.invalid-size", "login length must be 3-16");
                return;
            }
            if (userCredentials.getName().length() < 1 || userCredentials.getName().length() > 32) {
                errors.rejectValue("name", "name.invalid-size", "name length must be 1-32");
                return;
            }
            if (!userService.isLoginVacant(userCredentials.getLogin())) {
                errors.rejectValue("login", "login.is-in-use", "login is already in use");
            }
        }
    }
}
