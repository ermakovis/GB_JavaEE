package ru.ermakovis.persist.order;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository {
    @Transactional
    void insert(Order order);

    @Transactional
    void update(Order order);

    @Transactional
    void delete(int id);

    Order find(int id);

    List<Order> findAll();
}
