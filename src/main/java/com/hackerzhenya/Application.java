package com.hackerzhenya;

import com.hackerzhenya.services.AccountService;
import com.hackerzhenya.servlets.AuthServlet;
import com.hackerzhenya.servlets.FilesServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AccountService accountService = new AccountService();

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", new AuthServlet(accountService)).addMapping("/auth");
        context.addServlet("FilesServlet", new FilesServlet(accountService)).addMapping("/files");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
