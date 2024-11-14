package be.neoo.repository;

import be.neoo.entities.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepository {

    /**
     * get all cities
     * @param em
     * @return
     */
    public List<City> getCities(EntityManager em) {
        Query query = em.createNamedQuery("city.findAll", City.class);
        return query.getResultList();
    }

    public City findById(EntityManager em, int id) {
       return em.find(City.class, id);
    }
}
