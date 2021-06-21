package ru.ermakovis.persist.category;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository {
    @Transactional
    void insert(Category category);

    @Transactional
    void update(Category category);

    @Transactional
    void delete(Integer id);

    Category find(Integer id);

    List<Category> findAll();
}
