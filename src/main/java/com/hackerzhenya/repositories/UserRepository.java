package com.hackerzhenya.repositories;

import com.hackerzhenya.database.Repository;
import com.hackerzhenya.dto.User;
import org.hibernate.Session;

public class UserRepository extends Repository {
    public UserRepository(Session session) {
        super(session);
    }

    public void create(User user) {
        session.save(user);
    }

    public User findByLogin(String login) {
        return session.get(User.class, login);
    }
}
