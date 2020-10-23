package com.hackerzhenya.servlets;

import com.hackerzhenya.http.MiddlewareProcessor;
import com.hackerzhenya.middlewares.AuthMiddleware;
import com.hackerzhenya.middlewares.FilePathMiddleware;
import com.hackerzhenya.services.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilesServlet extends HttpServlet {
    private final MiddlewareProcessor middlewareProcessor;

    public FilesServlet(AccountService accountService) {
        this.middlewareProcessor = new MiddlewareProcessor(new ArrayList<>(){{
            add(new AuthMiddleware(accountService, "/auth"));
            add(new FilePathMiddleware("/Users"));
        }});
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (middlewareProcessor.Process(req, resp)) {
            super.service(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = (Path) req.getAttribute("path");
        File file = path.toFile();

        if (file.isDirectory()) {
            req.setAttribute("now", new SimpleDateFormat("MM.dd.yyyy HH:mm:ss").format(new Date()));
            req.setAttribute("path", path);
            req.setAttribute("files", scan(path));
            req.getRequestDispatcher("files.jsp").forward(req, resp);
        }

        if (file.isFile()) {
            resp.setHeader("Content-Type", "application/octet-stream");
            resp.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            new FileReader(file).transferTo(resp.getWriter());
        }
    }

    protected List<File> scan(Path path) throws IOException {
        return Files.list(path)
                .map(Path::toFile)
                .sorted(this::comparator)
                .collect(Collectors.toList());
    }

    protected int comparator(File a, File b) {
        return a.isDirectory() && b.isDirectory() || a.isFile() && b.isFile()
                ? a.compareTo(b)
                : Boolean.compare(a.isFile(), b.isFile());
    }
}
