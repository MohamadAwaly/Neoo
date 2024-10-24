package be.neoo.repository;

import be.neoo.entities.Employee;
import be.neoo.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {

    public Role findByName(EntityManager em, String role) {
        Query query = em.createNamedQuery("Role.findByName", Role.class);
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    public List<Role> findAllRoles(EntityManager em) {
        Query query = em.createNamedQuery("Role.findAll", Role.class);
        return query.getResultList();
    }

}
