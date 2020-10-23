package com.hackerzhenya.services;

import com.hackerzhenya.dto.User;
import com.hackerzhenya.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private final UserRepository userRepository;
    private final Map<String, User> session;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
        session = new HashMap<>();
    }

    public void addNewUser(User user) {
        userRepository.create(user);
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
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
