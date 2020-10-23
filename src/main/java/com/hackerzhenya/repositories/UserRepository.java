package com.hackerzhenya.repositories;

import com.hackerzhenya.database.Executor;
import com.hackerzhenya.database.Repository;
import com.hackerzhenya.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository extends Repository {
    public UserRepository(Executor executor) {
        super(executor);
    }

    private static User mapUser(ResultSet result) throws SQLException {
        return new User(
                result.getString("login"),
                result.getString("password"),
                result.getString("email")
        );
    }

    @Override
    protected void migrate() throws SQLException {
        this.executor.execute(
                "create table if not exists users " +
                        "(login varchar(256), password varchar(256), email varchar(256), primary key (login))"
        );
    }

    public void create(User user) {
        try {
            this.executor.execute(String.format(
                    "insert into users (login, password, email) values ('%s', '%s', '%s')",
                    user.getLogin(),
                    user.getPassword(),
                    user.getEmail()
            ));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User findByLogin(String login) {
        try {
            List<User> users = this.executor.query(
                    String.format("select * from users where login = '%s'", login),
                    UserRepository::mapUser
            );

            if (users.size() > 0) {
                return users.get(0);
            }

            return null;
        } catch (SQLException throwables) {
            return null;
        }
    }
}
