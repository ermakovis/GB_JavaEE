package ru.ermakovis.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ermakovis.webapp.persist.StoreItem;
import ru.ermakovis.webapp.persist.StoreItemRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CatalogServlet", urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CatalogServlet.class);
    private static StoreItemRepository repository;

    @Override
    public void init() throws ServletException {
        repository = (StoreItemRepository) getServletContext().getAttribute("storeItemRepository");
        if (repository == null) {
            throw new ServletException("Failed to initialize StoreItemRepository");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("get request");

        try {
            List<StoreItem> storeItemList = repository.findAll();
            req.setAttribute("pageName", "Catalog");
            req.setAttribute("storeItemList", storeItemList);
            getServletContext().getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(req, resp);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        resp.setHeader("PageName", "Каталог");
        getServletContext().getRequestDispatcher("/page_header").include(req, resp);
        resp.getWriter().println("<p>Catalog</p>");
    }
}
