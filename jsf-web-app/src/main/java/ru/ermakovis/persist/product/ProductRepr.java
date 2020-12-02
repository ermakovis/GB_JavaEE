package ru.ermakovis.persist.product;

import ru.ermakovis.persist.category.Category;

import java.io.Serializable;

public class ProductRepr implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;
    private String categoryName;

    public ProductRepr() {
    }

    public ProductRepr(Integer id, String name, String description, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = category == null ? null : category.getId();
        this.categoryName = category == null ? null : category.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
