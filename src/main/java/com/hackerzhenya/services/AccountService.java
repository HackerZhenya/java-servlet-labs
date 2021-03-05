package com.hackerzhenya.services;

import com.hackerzhenya.dto.User;
import com.hackerzhenya.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final SessionFactory sessionFactory;
    private final Map<String, User> session;

    public AccountService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        session = new HashMap<>();
    }

    public void addNewUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserRepository repository = new UserRepository(session);
        repository.create(user);
        transaction.commit();
        session.close();
    }

    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        UserRepository repository = new UserRepository(session);
        User user = repository.findByLogin(login);
        session.close();

        return user;
    }

    public User getUserBySessionId(String sessionId) {
        return session.get(sessionId);
    }

    public void addSession(String sessionId, User user) {
        session.put(sessionId, user);
    }

    public void deleteSession(String sessionId) {
        session.remove(sessionId);
    }
}
