package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends BasicRepository<User>{

    User findByLogin(String login);

    User findByEmail(String email);

    User findByLoginAndPasswordSha(String login, String passwordSha);

    User findByEmailAndPasswordSha(String email, String passwordSha);

    Long findCount();

    void save(User user, String passwordSha);

    void changeAdmin(long id, boolean admin);
}
