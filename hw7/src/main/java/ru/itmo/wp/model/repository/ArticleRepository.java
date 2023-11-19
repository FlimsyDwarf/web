package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository extends BasicRepository<Article> {
	void save(Article article);
	Article findByTitle(String title);
	List<Article> findAllByUserId(long id);

	Article findById(long id);

	void changeHidden(long id, Boolean hidden);

	List<Article> findAllNotHidden();
}
