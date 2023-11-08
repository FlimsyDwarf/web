package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.BasicRepository;
import ru.itmo.wp.model.repository.TalkRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class TalkRepositoryImpl extends BasicRepositoryImpl<Talk> implements TalkRepository {

	@Override
	public void save(Talk talk) {
		String sqlRequest = "INSERT INTO " + getTableName() + " (" +
				"`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())";
		super.save(sqlRequest, talk, talk.getSourceUserId(), talk.getTargetUserId(), talk.getText());
	}

	@Override
	public String getTableName() {
		return "Talk";
	}

	@Override
	public Talk toEntity(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}

		Talk talk = new Talk();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			switch (metaData.getColumnName(i)) {
				case "id":
					talk.setId(resultSet.getLong(i));
					break;
				case "sourceUserId":
					talk.setSourceUserId(resultSet.getString(i));
					break;
				case "targetUserId":
					talk.setTargetUserId(resultSet.getString(i));
					break;
				case "text":
					talk.setText(resultSet.getString(i));
					break;
				case "creationTime":
					talk.setCreationTime(resultSet.getTimestamp(i));
					break;
				default:
					// No operations.
			}
		}

		return talk;
	}

	@Override
	public Talk find(long id) {
		return super.find(id);
	}
	@Override
	public List<Talk> findAllById(String sourceId, String targetId) {
		return super.findAllBy(
				"WHERE sourceUserId=? OR targetUserId=? ORDER BY creationTime DESC",
				sourceId, targetId);
	}
}
