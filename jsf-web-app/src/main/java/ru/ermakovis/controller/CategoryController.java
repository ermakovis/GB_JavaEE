package ru.ermakovis.controller;

import ru.ermakovis.persist.Category;
import ru.ermakovis.persist.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category currentItem;

    private List<Category> categories;


    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.categories = categoryRepository.findAll();
    }


    public List<Category> getAllItems() {
        return categories;
    }

    public Category getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Category item) {
        this.currentItem = item;
    }

    public String saveItem() {
        if (currentItem.getId() == null) {
            categoryRepository.insert(currentItem);
        } else {
            categoryRepository.update(currentItem);
        }
        return "/categories.xhtml?faces-redirect=true";
    }

    public String editItem(Category item) {
        setCurrentItem(item);
        return "/category.xhtml?faces-redirect=true";
    }

    public void deleteItem(Category item) throws SQLException {
        categoryRepository.delete(item.getId());
    }

    public String createItem() {
        currentItem = new Category();
        return "/category.xhtml?faces-redirect=true";
    }
}
