package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Entity;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public interface BasicRepository<T extends Entity> {

	String getTableName();


	T toEntity(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException;

	T find(long id);

	T findBy(String sqlRequest, Object... parameters);

	List<T> findAllBy(String sqlRequest, Object... parameters);

	List<T> findAll();

	void save(String sqlRequest, Entity entity, Object... parameters);


}
