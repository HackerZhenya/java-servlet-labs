package com.hackerzhenya.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Middleware {
    boolean Handle(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
