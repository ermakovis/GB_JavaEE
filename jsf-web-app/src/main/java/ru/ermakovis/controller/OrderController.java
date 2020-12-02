package ru.ermakovis.controller;

import ru.ermakovis.persist.order.Order;
import ru.ermakovis.persist.order.OrderRepository;
import ru.ermakovis.persist.order.OrderRepr;
import ru.ermakovis.persist.product.ProductRepr;
import ru.ermakovis.service.OrderService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {
    @Inject
    private OrderService orderService;

    public List<OrderRepr> getAll() {
        return orderService.getAll();
    }

    public void addItem(ProductRepr productRepr) {
        orderService.add(productRepr);
    }

}

