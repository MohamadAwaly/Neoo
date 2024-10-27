package be.neoo.repository;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import be.neoo.entities.Brand;

import java.util.List;

@Repository
public class BrandRepository {

    /**
     * Add Brand
     * @param em
     * @param brand
     * @return
     */
    public Brand save(EntityManager em, Brand brand) {
        em.persist(brand);
        return em.find(Brand.class, brand.getId());
    }

    /**
     * List brand
     * @param em
     * @return
     */
    public List<Brand> getBrands(EntityManager em) {
        Query query = em.createNamedQuery("brand.findAll", Brand.class);
        return query.getResultList();
    }

    /**
     * update brand
     * @param em
     * @param id
     * @param brand
     * @return
     */
    public Brand update (EntityManager em, int id, Brand brand) {
        Brand oldBrand = em.find(Brand.class, id);
        if (oldBrand != null) {
            oldBrand.setName(brand.getName());
            em.merge(oldBrand);
        }
        return oldBrand;
    }
}
