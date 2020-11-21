package ru.ermakovis.persist;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class CatalogItem {
    private Integer id;

    @NotNull
    @Size(min = 4, max = 40, message = "Name should be in range of 4 to 40")
    private String name;
    private String image;

    @NotNull
    private Integer amount;

    public CatalogItem() {
    }

    public CatalogItem(Integer id, String name, String image, Integer amount) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", amount=" + amount +
                '}';
    }
}
