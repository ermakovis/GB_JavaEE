package ru.ermakovis.persist.category;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository {
    @Transactional
    void insert(Category category);

    @Transactional
    void update(Category category);

    @Transactional
    void delete(int id);

    Category find(int id);

    List<Category> findAll();
}
