package ru.ermakovis.persist.product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }

    @Override
    @Transactional
    public void update(Product product) {
        em.merge(product);
    }

    @Override
    @Transactional
    public void delete(int id) {
        Product item = em.find(Product.class, id);
        if (item != null) {
            em.remove(item);
        }
    }

    @Override
    public Product find(int id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("FROM Product c", Product.class).getResultList();
    }

    @Override
    public ProductRepr findProductReprById(long id) {
        return em.createQuery("select new ru.ermakovis.persist.product.ProductRepr(p.id, p.name, p.description, c) " +
                "from Product p " +
                "left join p.category c " +
                "where p.id = :id", ProductRepr.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<ProductRepr> findAllProductRepr() {
        return em.createQuery("select new ru.ermakovis.persist.product.ProductRepr(p.id, p.name, p.description, c) " +
                "from Product p " +
                "left join p.category c", ProductRepr.class)
                .getResultList();
    }


}
