package ru.ermakovis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ermakovis.persist.category.Category;
import ru.ermakovis.persist.category.CategoryRepository;
import ru.ermakovis.persist.product.Product;
import ru.ermakovis.persist.product.ProductRepository;
import ru.ermakovis.persist.product.ProductRepr;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @TransactionAttribute
    public void insert(ProductRepr productRepr) {
        logger.info("insert call");
        Category category = categoryRepository.find(productRepr.getId());
        productRepository.insert(new Product(null, productRepr.getName(),
                productRepr.getDescription(), category));
    }

    @TransactionAttribute
    public void update(ProductRepr productRepr) {
        logger.info("update call");
        Category category = categoryRepository.find(productRepr.getId());
        productRepository.update(new Product(null, productRepr.getName(),
                productRepr.getDescription(), category));

    }

    @TransactionAttribute
    public void delete(Integer id) {
        productRepository.delete(id);
    }

    public ProductRepr find(Integer id) {
        return productRepository.findProductReprById(id);
    }

    public List<ProductRepr> findAllRepr() {
        return productRepository.findAllProductRepr();
    }
}


