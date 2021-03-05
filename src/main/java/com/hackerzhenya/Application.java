package com.hackerzhenya;

import com.hackerzhenya.dto.User;
import com.hackerzhenya.services.AccountService;
import com.hackerzhenya.servlets.AuthServlet;
import com.hackerzhenya.servlets.FilesServlet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        SessionFactory sessionFactory = createSessionFactory();
        AccountService accountService = new AccountService(sessionFactory);

        ServletContext context = sce.getServletContext();
        context.addServlet("AuthServlet", new AuthServlet(accountService)).addMapping("/auth");
        context.addServlet("FilesServlet", new FilesServlet(accountService)).addMapping("/files");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/java_servlet_lab");
        configuration.setProperty("hibernate.connection.username", "usr");
        configuration.setProperty("hibernate.connection.password", "usr");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration
                .buildSessionFactory(serviceRegistry);
    }
}
