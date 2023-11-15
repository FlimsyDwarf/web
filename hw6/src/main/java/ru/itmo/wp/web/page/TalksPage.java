package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class TalksPage extends Page {
	private void action(HttpServletRequest request, Map<String, Object> view) {
		User user = getUser(request);
		if (user == null) {
			setMessage("You need to be logged in to visit Talks page");
			throw new RedirectException("/index");
		}
		try {
			userService.validateById(user.getId());
		} catch (ValidationException e) {
			setMessage("You must be logged in to access the talks");
			throw new RedirectException("/index");
		}
		String id = String.valueOf(user.getId());
		view.put("talks", talkService.findAllById(id));
		view.put("users", userService.findAll());
	}

	@Override
	public void before(HttpServletRequest request, Map<String, Object> view)  {
		super.before(request, view);
	}

	private void send(HttpServletRequest request, Map<String, Object> view) {
		Talk talk = new Talk();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String targetUserId = request.getParameter("targetUserId");
		String text = request.getParameter("text");
		if (user == null) {
			setMessage("You must be logged in to send the talks");
			throw new RedirectException("/index");
		}
		try {
			userService.validateById(user.getId());
		} catch (ValidationException e) {
			setMessage("You must be logged in to send the talks");
			throw new RedirectException("/index");
		}
		try {
			userService.validateById(Long.parseLong(targetUserId));
		} catch (ValidationException | NumberFormatException e) {
			setMessage("User you try to send message to does not exist");
			throw new RedirectException("/talks");
		}
        try {
			talkService.validateText(text);
		} catch (ValidationException e) {
			setMessage("text can't be blank");
			throw new RedirectException("/talks");
		}
		talk.setSourceUserId(String.valueOf(user.getId()));
		talk.setTargetUserId(targetUserId);
		talk.setText(text);
		talkService.send(talk);

		session.setAttribute("message", "Message is sent");
		throw new RedirectException("/talks");
	}
}
