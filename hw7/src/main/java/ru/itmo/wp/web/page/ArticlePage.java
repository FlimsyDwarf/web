package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ArticlePage extends Page {
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
	}


	private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		articleService.validateArticle(title, text);
		articleService.save(title, text, getUser(request).getId());
		setMessage("article is saved");
		throw new RedirectException("/article");
	}
}
