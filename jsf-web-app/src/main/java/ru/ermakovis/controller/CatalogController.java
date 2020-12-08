package ru.ermakovis.controller;

import ru.ermakovis.persist.category.CategoryRepository;
import ru.ermakovis.persist.product.Product;
import ru.ermakovis.persist.product.ProductRepr;
import ru.ermakovis.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogController implements Serializable {

    @Inject
    private ProductService productService;

    private ProductRepr currentProduct;
    private List<ProductRepr> items;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.items = productService.findAllRepr();
    }


    public List<ProductRepr> getAllItems() {
        return items;
    }

    public ProductRepr getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(ProductRepr product) {
        this.currentProduct = product;
    }

    public String saveItem() {
        if (currentProduct.getId() == null) {
            productService.insert(currentProduct);
        } else {
            productService.update(currentProduct);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public String editItem(ProductRepr product) {
        setCurrentProduct(product);
        return "/item.xhtml";
    }

    public void deleteItem(Product item) throws SQLException {
        productService.delete(item.getId());
    }

    public String createItem() {
        currentProduct = new ProductRepr();
        return "/item.xhtml?faces-redirect=true";
    }
}
