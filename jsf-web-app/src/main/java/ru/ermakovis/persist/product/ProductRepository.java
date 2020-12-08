package ru.ermakovis.persist.product;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository {
    @Transactional
    void insert(Product product);

    @Transactional
    void update(Product product);

    @Transactional
    void delete(Integer id);

    Product find(Integer id);

    List<Product> findAll();

    ProductRepr findProductReprById(Integer id);

    List<ProductRepr> findProductReprByName(String name);

    List<ProductRepr> findProductReprByCategoryName(String name);

    List<ProductRepr> findAllProductRepr();
}
