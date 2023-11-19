package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserService {
    private static final String PASSWORD_SALT = "1174f9d7bc21e00e9a5fd0a783a44c9a9f73413c";

    private final UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user, String password, String passwordConfirmation) throws ValidationException {
        if (Strings.isNullOrEmpty(user.getLogin())) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8 letters");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (Strings.isNullOrEmpty(password)) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4 characters");
        }
        if (password.length() > 64) {
            throw new ValidationException("Password can't be longer than 64 characters");
        }
        if (!password.equals(passwordConfirmation)) {
            throw new ValidationException("Passwords must match");
        }
        if (Strings.isNullOrEmpty(user.getEmail())) {
            throw new ValidationException("Email is required");
        }
        if (!user.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ValidationException("Incorrect email");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new ValidationException("Email is already in use");
        }
    }

    public void register(User user, String password) {
        userRepository.save(user, getPasswordSha(password));
    }

    private String getPasswordSha(String password) {
        return Hashing.sha256().hashBytes((PASSWORD_SALT + password).getBytes(StandardCharsets.UTF_8)).toString();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void validateEnter(String loginOrEmail, String password) throws ValidationException {
        User userBylogin = userRepository.findByLoginAndPasswordSha(loginOrEmail, getPasswordSha(password));
        User userByEmail = userRepository.findByEmailAndPasswordSha(loginOrEmail, getPasswordSha(password));
        if (userBylogin == null && userByEmail == null) {
            throw new ValidationException("Invalid login/email or password");
        }
    }

    public void validateUser(User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("You are not logged in");
        }
        validateById(user.getId());
    }

    public void validateByLoginOrEmail(String loginOrEmail) throws ValidationException {
        User userBylogin = userRepository.findByLogin(loginOrEmail);
        User userByEmail = userRepository.findByEmail(loginOrEmail);
        if (userBylogin == null && userByEmail == null) {
            throw new ValidationException("No user with this login/email: " + loginOrEmail);
        }
    }

    public void validateById(long id) throws ValidationException {
        User user = userRepository.find(id);
        if (user == null) {
            throw new ValidationException("No user with this login/email");
        }
    }

    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPasswordSha(login, getPasswordSha(password));
    }

    public User findByEmail(String loginOrEmail) {
        return userRepository.findByEmail(loginOrEmail);
    }

    public User findById(long id) {
        return userRepository.find(id);
    }

    public Long findCount() {
        return userRepository.findCount();
    }

    public UserRepository getRepository() {
        return userRepository;
    }

    public void changeAdmin(long id, boolean admin) {
        userRepository.changeAdmin(id, admin);
    }
}
