package ru.ermakovis.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Transactional
    public void insert(Category category) {
        em.persist(category);
    }

    @Transactional
    public void update(Category category) {
        em.merge(category);
    }

    @Transactional
    public void delete(int id) {
        Category item = em.find(Category.class, id);
        if (item != null) {
            em.remove(item);
        }
    }

    public Category find(int id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("FROM Category c", Category.class).getResultList();
    }
}
