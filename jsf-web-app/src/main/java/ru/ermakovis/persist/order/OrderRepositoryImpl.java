package ru.ermakovis.persist.order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Order order) {
        em.persist(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        em.merge(order);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }
    }

    @Override
    public Order find(int id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }
}
