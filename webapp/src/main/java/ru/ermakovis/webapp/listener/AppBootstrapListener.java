package ru.ermakovis.webapp.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ermakovis.webapp.persist.StoreItemRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppBootstrapListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("App initialization");

        ServletContext context = sce.getServletContext();
        String jdbcConnectionString = context.getInitParameter("jdbcConnectionString");
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");

        try {
            Connection connection = DriverManager.getConnection(jdbcConnectionString, username, password);
            context.setAttribute("connection", connection);

            context.setAttribute("storeItemRepository", new StoreItemRepository(connection));

        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Closing connection");
        ServletContext context = sce.getServletContext();
        Connection connection = (Connection) context.getAttribute("connection");

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Failed to close connection to DB");
        }

    }
}
