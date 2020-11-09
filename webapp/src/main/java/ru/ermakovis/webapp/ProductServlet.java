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

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);
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
            StoreItem item = repository.find(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("pageName", item.getName());
            req.setAttribute("storeItem", item);
            getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }


    }
}
