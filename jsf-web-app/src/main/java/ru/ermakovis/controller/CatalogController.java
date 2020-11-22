package ru.ermakovis.controller;

import ru.ermakovis.persist.Product;
import ru.ermakovis.persist.CatalogRepository;
import ru.ermakovis.persist.Category;

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
    private CatalogRepository catalogRepository;


    private Product currentProduct;

    private List<Product> items;
    private List<Category> categories;


    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.items = catalogRepository.findAll();
    }


    public List<Product> getAllItems() {
        return items;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product product) {
        this.currentProduct = product;
    }

    public String saveItem() {
        if (currentProduct.getId() == null) {
            catalogRepository.insert(currentProduct);
        } else {
            catalogRepository.update(currentProduct);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public String editItem(Product product) {
        setCurrentProduct(product);
        return "/item.xhtml";
    }

    public void deleteItem(Product item) throws SQLException {
        catalogRepository.delete(item.getId());
    }

    public String createItem() {
        currentProduct = new Product();
        return "/item.xhtml?faces-redirect=true";
    }
}
