package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {

	protected final UserService userService = new UserService();
	protected final EventService eventService = new EventService();
	protected final TalkService talkService = new TalkService();
	private HttpServletRequest request;
	public void before(HttpServletRequest request, Map<String, Object> view) {
		this.request = request;
		putUserCount(view);
		putUser(view);
		putMessage(view);
	}

	public void after(HttpServletRequest request, Map<String, Object> view) {
		//putMessage(view);
	}


	protected User getUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}
	protected void putUser(Map<String, Object> view) {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			view.put("user", user);
		}
	}

	protected void putUserCount(Map<String, Object> view) {
		Long userCount = userService.findCount();
		view.put("userCount", userCount);
	}

	protected void putMessage(Map<String, Object> view) {
		String message = (String) request.getSession().getAttribute("message");
		if (!Strings.isNullOrEmpty(message)) {
			view.put("message", message);
			request.getSession().removeAttribute("message");
		}
	}

	protected void setAttribute(String attribute, Object object) {
		request.getSession().setAttribute(attribute, object);
	}
	protected void setMessage(String message) {
		//request.getSession().setAttribute("message", message);
		setAttribute("message", message);
	}


	protected void setUser(User user) {
		//request.getSession().setAttribute("user", user);
		setAttribute("user", user);
	}

	protected void removeAttribute(String attribute) {
		request.getSession().removeAttribute(attribute);
	}
}
