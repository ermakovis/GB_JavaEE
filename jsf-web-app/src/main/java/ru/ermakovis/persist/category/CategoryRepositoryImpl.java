package ru.ermakovis.persist.category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepositoryImpl implements CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Category category) {
        em.persist(category);
    }

    @Override
    @Transactional
    public void update(Category category) {
        em.merge(category);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Category item = em.find(Category.class, id);
        if (item != null) {
            em.remove(item);
        }
    }

    @Override
    public Category find(int id) {
        return em.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("FROM Category c", Category.class).getResultList();
    }
}
