package be.neoo.repository;

import be.neoo.entities.Brand;
import be.neoo.entities.Permission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepository {

    public List<Permission> getPermissions(EntityManager em) {
        Query query = em.createNamedQuery("permission.findAll", Brand.class);
        return query.getResultList();
    }
}
