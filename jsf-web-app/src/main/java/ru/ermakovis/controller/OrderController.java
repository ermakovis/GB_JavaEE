package ru.ermakovis.controller;

import ru.ermakovis.persist.Order;
import ru.ermakovis.persist.OrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@ApplicationScoped
public class OrderController implements Serializable {

    @Inject
    private OrderRepository orderRepository;

    private Order currentItem;

    private List<Order> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.categories = orderRepository.findAll();
    }

    public List<Order> getAllItems() {
        return categories;
    }

    public Order getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Order item) {
        this.currentItem = item;
    }

    public String saveItem() {
        if (currentItem.getId() == null) {
            orderRepository.insert(currentItem);
        } else {
            orderRepository.update(currentItem);
        }
        return "/orders.xhtml?faces-redirect=true";
    }

    public String editItem(Order item) {
        setCurrentItem(item);
        return "/order.xhtml?faces-redirect=true";
    }

    public void deleteItem(Order item) {
        orderRepository.delete(item.getId());
    }

    public String createItem() {
        currentItem = new Order();
        return "/order.xhtml?faces-redirect=true";
    }
}

