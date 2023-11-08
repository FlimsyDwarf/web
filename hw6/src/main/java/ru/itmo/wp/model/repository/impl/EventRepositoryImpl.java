package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Entity;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.EventRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;

public class EventRepositoryImpl extends BasicRepositoryImpl<Event> implements EventRepository {
	private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

	@Override
	public String getTableName() {
		return "Event";
	}

	@Override
	public Event toEntity(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
		if (!resultSet.next()) {
			return null;
		}
		Event event = new Event();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			switch (metaData.getColumnName(i)) {
				case "userId":
					event.setUserId(resultSet.getLong(i));
					break;
				case "type":
					event.setType(Event.Type.valueOf(resultSet.getString(i)));
					break;
				default:
					// No operations.
			}
		}
		return event;
	}

	@Override
	public void save(Event event) {
		String sqlRequest = "INSERT INTO " + getTableName() +
				" (`userId`, `type`, `creationTime`) VALUES (?, ?, NOW())";
		super.save(sqlRequest, event, event.getUserId(), event.getType());
	}

	@Override
	public Event findByUserId(long userId) {
		return null;
	}

}
