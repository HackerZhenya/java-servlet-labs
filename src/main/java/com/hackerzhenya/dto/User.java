package com.hackerzhenya.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -5170875020617735653L;

    @Id
    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    protected User() {}

    public User(String login, String password, String email) {
        this(login, password);
        this.setEmail(email);
    }

    public User(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return String.format("User {login=%s, email=%s}", login, email);
    }
}
