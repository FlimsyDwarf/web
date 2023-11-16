package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;


import java.sql.*;

@SuppressWarnings("SqlNoDataSourceInspection")
public class UserRepositoryImpl extends BasicRepositoryImpl<User> implements UserRepository {

    @Override
    public User findByEmail(String email) {
        return super.findBy("WHERE email=?", email);
    }

    @Override
    public User findByLogin(String login) {
        return super.findBy("WHERE login=?", login);
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return super.findBy("WHERE login=? AND passwordSha=?", login, passwordSha);
    }

    @Override
    public User findByEmailAndPasswordSha(String email, String passwordSha) {
        return super.findBy("WHERE email=? AND passwordSha=?", email, passwordSha);
    }

    @Override
    public Long findCount() {
        return (long) findAll().size();
    }

    @Override
    public User toEntity(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "email":
                    user.setEmail(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    @Override
    public void save(User user, String passwordSha) {
        String sqlRequest = "INSERT INTO " + getTableName() + " (" +
                "`login`, `email`, `passwordSha`, `creationTime`) VALUES (?, ?, ?, NOW())";
        super.save(sqlRequest, user, user.getLogin(), user.getEmail(), passwordSha);
    }

    @Override
    public String getTableName() {
        return "User";
    }

}
