package be.neoo.repository;

import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import be.neoo.entities.Customer;

import java.util.List;

@Repository
public class CustomerRepository {

    public List<Customer> findAll(EntityManager em) {
        Query query = em.createNamedQuery("customer.list", Customer.class);
        return query.getResultList();
    }
}
