package be.neoo.repository;


import be.neoo.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    /**
     * add Category
     * @param em
     * @param category
     * @return
     */
    public Category save(EntityManager em, Category category) {
        em.persist(category);
        return em.find(Category.class, category.getId());
    }

    /**
     * List Categories
     * @param em
     * @return
     */
    public List<Category> getCategories(EntityManager em) {
        Query query = em.createNamedQuery("category.findAll", Category.class);
        return query.getResultList();
    }

    /**
     * update category
     * @param em
     * @param id
     * @param category
     * @return
     */
    public Category update (EntityManager em, int id, Category category) {
        Category oldCategory = em.find(Category.class, id);
        if (oldCategory != null) {
            oldCategory.setName(category.getName());
            em.merge(oldCategory);
        }
        return category;
    }
}
