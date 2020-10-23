package com.hackerzhenya.servlets;

import com.hackerzhenya.dto.User;
import com.hackerzhenya.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private final AccountService accountService;

    public AuthServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "logout":
                logout(req, resp);
                break;

            case "register":
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                break;

            default:
            case "auth":
                req.getRequestDispatcher("auth.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");

        if (action != null && action.equals("register")) {
            register(req, resp);
        } else {
            authorize(req, resp);
        }
    }

    private void authorize(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = accountService.getUserByLogin(req.getParameter("login"));

        if (user != null && user.verifyPassword(req.getParameter("password"))) {
            accountService.addSession(req.getSession().getId(), user);
        }

        resp.sendRedirect("/files");
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = new User(
                    req.getParameter("login"),
                    req.getParameter("password"),
                    req.getParameter("email")
            );

            accountService.addNewUser(user);
            accountService.addSession(req.getSession().getId(), user);
            resp.sendRedirect("/files");
        } catch (Exception e){
            resp.sendRedirect("/auth?action=register");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        accountService.deleteSession(req.getSession().getId());
        resp.sendRedirect("/auth");
    }
}
