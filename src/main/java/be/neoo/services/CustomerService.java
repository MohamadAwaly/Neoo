package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.entities.Customer;
import be.neoo.entities.Employee;
import be.neoo.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        EntityManager em = EMF.getEM();
        return customerRepository.findAll(em);
    }
}
