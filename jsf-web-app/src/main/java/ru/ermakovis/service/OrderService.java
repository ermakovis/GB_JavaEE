package ru.ermakovis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ermakovis.persist.order.OrderRepr;
import ru.ermakovis.persist.product.ProductRepr;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final List<OrderRepr> orderList = new ArrayList<>();

    public List<OrderRepr> getAll() {
        return orderList;
    }

    public void add(ProductRepr productRepr) {
        logger.info("addCall");
        for (OrderRepr orderRepr : orderList) {
            if (orderRepr.getProductName().equals(productRepr.getName())) {
                orderRepr.setQuantity(orderRepr.getQuantity() + 1);
                return;
            }
        }
        orderList.add(new OrderRepr(null, productRepr.getName(), 1));
    }

}
