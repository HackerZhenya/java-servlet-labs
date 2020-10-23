package com.hackerzhenya.middlewares;

import com.hackerzhenya.dto.User;
import com.hackerzhenya.http.Middleware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathMiddleware implements Middleware {
    private final String homeDirectory;

    public FilePathMiddleware(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    @Override
    public boolean Handle(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getAttribute("auth");

        String _path = req.getParameter("path");
        Path path = Paths.get(_path == null ? "/" : _path);
        Path home = Paths.get(homeDirectory, user.getLogin());

        req.setAttribute("path", path.startsWith(home) ? path : home);
        return true;
    }
}
