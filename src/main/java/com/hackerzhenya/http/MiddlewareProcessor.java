package com.hackerzhenya.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MiddlewareProcessor {
    private final List<Middleware> middlewares;

    public MiddlewareProcessor(List<Middleware> middlewares) {
        this.middlewares = middlewares;
    }

    public boolean Process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        for (Middleware middleware : middlewares) {
            if (!middleware.Handle(req, resp)) {
                return false;
            }
        }

        return true;
    }
}
