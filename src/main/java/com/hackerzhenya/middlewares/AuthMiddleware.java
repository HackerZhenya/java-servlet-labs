package com.hackerzhenya.middlewares;


import com.hackerzhenya.dto.User;
import com.hackerzhenya.http.Middleware;
import com.hackerzhenya.services.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthMiddleware implements Middleware {
    private final AccountService accountService;
    private final String redirectTo;

    public AuthMiddleware(AccountService accountService, String redirectTo) {
        this.accountService = accountService;
        this.redirectTo = redirectTo;
    }

    @Override
    public boolean Handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User authUser = accountService.getUserBySessionId(req.getSession().getId());

        if (authUser == null) {
            resp.sendRedirect(redirectTo);
            return false;
        }

        req.setAttribute("auth", authUser);
        return true;
    }
}
