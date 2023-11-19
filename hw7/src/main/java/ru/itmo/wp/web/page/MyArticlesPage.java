package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyArticlesPage extends Page {
	private void action(HttpServletRequest request, Map<String, Object> view) {
		User user = getUser(request);
		try {
			userService.validateUser(user);
		} catch (ValidationException e) {
			setMessage("You need to be logged in to visit My Articles page");
			throw new RedirectException("/index");
		}
//		if (user == null) {
//			setMessage("You need to be logged in to visit My Articles page");
//			throw new RedirectException("/index");
//		}
//		try {
//			userService.validateById(user.getId());
//		} catch (ValidationException e) {
//			setMessage("You must be logged in to access My Articles page");
//			throw new RedirectException("/index");
//		}
	}

	@Override
	public void before(HttpServletRequest request, Map<String, Object> view) {
		super.before(request, view);
		User user = getUser(request);
		try {
			userService.validateUser(user);
			List<Article> articles = articleService.findAllByUserId(user.getId());
			view.put("articles", articles);
		} catch (ValidationException e) {
			setMessage("You need to be logged in to visit My Articles page");
		}
	}

	public void changeHidden(HttpServletRequest request, Map<String, Object> view) throws ValidationException, NumberFormatException {
		Boolean hidden = request.getParameter("hidden").equals("Hide");
		long userId = getUser(request).getId();
		long articleId = Long.parseLong(request.getParameter("articleId"));
		Article article = articleService.findByArticleId(articleId);
		if (userId != article.getUserId()) {
			throw new ValidationException("Its not your article");
		}
		articleService.validateArticleById(articleId);
		articleService.changeHidden(articleId, hidden);
		view.put("hidden", hidden ? "Show" : "Hide");
		view.put("message", "Show mode changed");
		//setMessage("Show mode changed");
	}
}
