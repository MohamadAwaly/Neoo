package be.neoo.repository;


import be.neoo.entities.Address;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AddressRepository {

    public List<Address> findAll(EntityManager em) {
        return null;
    }
}
