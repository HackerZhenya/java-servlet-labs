package com.hackerzhenya.services;

import com.hackerzhenya.dto.User;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final Map<String, User> users;
    private final Map<String, User> session;

    public AccountService() {
        users = new HashMap<>();
        session = new HashMap<>();
    }

    public void addNewUser(User user) {
        users.put(user.getLogin(), user);
    }

    public User getUserByLogin(String login) {
        return users.get(login);
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
