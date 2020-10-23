package com.hackerzhenya.dto;

public class User {
    private final String login;
    private final String pass;
    private final String email;

    public User(String login, String pass, String email) {
        validateNotEmpty("login", login);
        validateNotEmpty("pass", pass);
        validateNotEmpty("email", email);

        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return pass;
    }

    public boolean verifyPassword(String password) {
        return pass.equals(password);
    }

    private void validateNotEmpty(String name, String value) {
        if (value == null || value.length() == 0) {
            throw new RuntimeException(String.format("Поле \"%s\" не должно быть пустым", name));
        }
    }
}
