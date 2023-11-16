package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;


public class ArticleService {
	private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
	public void validateArticle(String title, String text) throws ValidationException {
		if (Strings.isNullOrEmpty(title)) {
			throw new ValidationException("Title is required");
		}
		if (title.length() > 255) {
			throw new ValidationException("Title can't be longer than 255 letters");
		}
		if (findByTitle(title) != null) {
			throw new ValidationException("Title is already in use");
		}
		if (Strings.isNullOrEmpty(text)) {
			throw new ValidationException("Text is required");
		}
	}

	public void validateArticleById(long id) throws ValidationException {
		if (articleRepository.findById(id) == null) {
			throw new ValidationException("Article with this id doesn't exist");
		}
	}

	public void save(String title, String text, long userId) {
		Article article = new Article();
		article.setTitle(title);
		article.setText(text);
		article.setUserId(userId);
		article.setHidden(false);
		articleRepository.save(article);
	}

	public Article findByTitle(String title) {
		return articleRepository.findByTitle(title);
	}

	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	public List<Article> findAllByUserId(long id) {
		return articleRepository.findAllByUserId(id);
	}

	public void changeHidden(long id, Boolean hidden) {
		articleRepository.changeHidden(id, hidden);
	}

}
