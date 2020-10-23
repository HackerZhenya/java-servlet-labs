package com.hackerzhenya;

import com.hackerzhenya.database.Executor;
import com.hackerzhenya.repositories.UserRepository;
import com.hackerzhenya.services.AccountService;
import com.hackerzhenya.servlets.AuthServlet;
import com.hackerzhenya.servlets.FilesServlet;
import org.postgresql.Driver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Executor executor = new Executor(connectToDatabase());
        UserRepository userRepository = new UserRepository(executor);
        AccountService accountService = new AccountService(userRepository);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", new AuthServlet(accountService)).addMapping("/auth");
        context.addServlet("FilesServlet", new FilesServlet(accountService)).addMapping("/files");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private Connection connectToDatabase() {
        try {
            DriverManager.registerDriver(new Driver());

            return DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/java_servlet_lab",
                    "usr",
                    "usr"
            );
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
