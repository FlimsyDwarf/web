package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.ArticleRepository;

import java.sql.*;
import java.util.List;

public class ArticleRepositoryImpl extends BasicRepositoryImpl<Article>  implements ArticleRepository {
	@Override
	public String getTableName() {
		return "Article";
	}

	@Override
	public Article toEntity(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}
		Article article = new Article();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			switch (metaData.getColumnName(i)) {
				case "id":
					article.setId(resultSet.getLong(i));
					break;
				case "userId":
					article.setUserId(resultSet.getLong(i));
					break;
				case "title":
					article.setTitle(resultSet.getString(i));
					break;
				case "text":
					article.setText(resultSet.getString(i));
					break;
				case "creationTime":
					article.setCreationTime(resultSet.getTimestamp(i));
					break;
				case "hidden":
					article.setHidden(resultSet.getBoolean(i));
				default:
					// No operations.
			}
		}
		return article;
	}

	@Override
	public void save(Article article) {
		String sqlRequest = "INSERT INTO " + getTableName() + " (" +
				"`userId`, `title`, `text`, `hidden`, `creationTime`) VALUES (?, ?, ?, ?, NOW())";
		super.save(sqlRequest, article, article.getUserId(), article.getTitle(),
				article.getText(), article.isHidden());
	}

	@Override
	public Article findByTitle(String title) {
		return super.findBy("WHERE title=?", title);
	}

	@Override
	public List<Article> findAllByUserId(long id) {
		return super.findAllBy("WHERE userId=?", id);
	}

	@Override
	public Article findById(long id) {
		return super.findBy("WHERE id=?", id);
	}

	@Override
	public void changeHidden(long id, Boolean hidden) {
		String sqlRequest = "UPDATE Article SET hidden = ? WHERE id=?";
		try (Connection connection = DATA_SOURCE.getConnection()) {
			try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
				super.setParameters(statement, hidden, id);
				if (statement.executeUpdate() != 1) {
					throw new RepositoryException("Can't save " + getTableName());
				}
			}
		} catch (SQLException e) {
			throw new RepositoryException("Can't find " + getTableName(), e);
		}
//		return super.changeField(sqlRequest, !current.isHidden(), id);
	}

	@Override
	public List<Article> findAllNotHidden() {
		return super.findAllBy("WHERE HIDDEN=0");
	}

}
