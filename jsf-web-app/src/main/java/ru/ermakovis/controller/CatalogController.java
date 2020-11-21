package ru.ermakovis.controller;

import ru.ermakovis.persist.CatalogItem;
import ru.ermakovis.persist.CatalogRepository;

import javax.enterprise.context.ApplicationScoped;
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
    private CatalogItem currentItem;

    public List<CatalogItem> getAllItems() throws SQLException {
        return catalogRepository.findAll();
    }

    public CatalogItem getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(CatalogItem item) {
        this.currentItem = item;
    }

    public String saveItem() throws SQLException {
        if (currentItem.getId() == null) {
            catalogRepository.insert(currentItem);
        } else {
            catalogRepository.update(currentItem);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public String editItem(CatalogItem item) {
        setCurrentItem(item);
        return "/item.xhtml?faces-redirect=true";
    }

    public void deleteItem(CatalogItem item) throws SQLException {
        catalogRepository.delete(item.getId());
    }

    public String createItem() {
        currentItem = new CatalogItem();
        return "/item.xhtml?faces-redirect=true";
    }
}
