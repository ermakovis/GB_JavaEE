package ru.ermakovis.persist.product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository {
    @Transactional
    void insert(Product product);

    @Transactional
    void update(Product product);

    @Transactional
    void delete(int id);

    Product find(int id);

    List<Product> findAll();

    ProductRepr findProductReprById(long id);

    List<ProductRepr> findAllProductRepr();
}
