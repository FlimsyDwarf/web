package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(view);
    }

    @Override
    public void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    public void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }

//    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
//        String message = (String) request.getSession().getAttribute("message");
//        if (!Strings.isNullOrEmpty(message)) {
//            view.put("message", message);
//            request.getSession().removeAttribute("message");
//        }
//    }
}
