package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", User.COLOR.RED),
            new User(6, "pashka", "Pavel Mavrin", User.COLOR.RED),
            new User(9, "geranazavr555", "Georgiy Nazarov", User.COLOR.GREEN),
            new User(10, "test", "testov", User.COLOR.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", User.COLOR.GREEN)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "First post", "Lorem ipsum dolor sit amet," +
                    " consetetur sadipscing elitr," +
                    " sed diam nonumy eirmod tempor" +
                    " invidunt ut labore et dolore" +
                    " magna aliquyam",
                    1),
            new Post(2, "second post", "elit fusce conubia legere nisl", 11),
            new Post(5, "third post", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed dia",
                    10),
            new Post(6, "third post", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed dia",
                             1)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
