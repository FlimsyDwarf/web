package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class UsersPage extends Page {

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }

    private void findUser(HttpServletRequest request, Map<String, Object> view) throws NumberFormatException {
        view.put("user",
                userService.findById(Long.parseLong(request.getParameter("userId"))));
    }

    private void changeAdmin(HttpServletRequest request, Map<String, Object> view) throws ValidationException, NumberFormatException {
        String idStr = request.getParameter("userId");
        long id = Long.parseLong(idStr);
        boolean admin = request.getParameter("admin").equals("Enable");
        userService.validateById(id);
        User changedUser = userService.findById(id);
        User sessionUser = getUser(request);
        userService.validateUser(sessionUser);
        User curUser = userService.findById(sessionUser.getId());
        userService.validateUser(curUser);
        if (!curUser.getAdmin()) {
            setUser(curUser);
            setMessage("You should be admin");
            throw new RedirectException("/index");
        }
        userService.changeAdmin(id, admin);
        view.put("admin", admin);
        view.put("message", "admin status changed");
    }
}
