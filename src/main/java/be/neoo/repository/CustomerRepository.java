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

    public Customer disableCustomer(EntityManager em, int id) {
        Customer customer = em.find(Customer.class, id);

        if (customer != null) {
            if (customer.isActif()) {
                customer.setActif(false);
            } else {
                customer.setActif(true);
            }
            em.merge(customer);
            return customer;
        }
        return null;
    }

    public Customer updateCustomer (EntityManager em,int id, Customer updateCustomer) {
        Customer existingCustomer = em.find(Customer.class, id);
        if (existingCustomer != null) {
            existingCustomer.setLogin(updateCustomer.getLogin());
            existingCustomer.setLastName(updateCustomer.getLastName());
            existingCustomer.setFirstName(updateCustomer.getFirstName());
            existingCustomer.setBirthday(updateCustomer.getBirthday());
            existingCustomer.setInscriptionDate(updateCustomer.getInscriptionDate());
            existingCustomer.setVat(updateCustomer.getVat());
            existingCustomer.setMail(updateCustomer.getMail());
            existingCustomer.setStatus(updateCustomer.getStatus());
            existingCustomer.setActif(updateCustomer.isActif());
            existingCustomer.setRole(updateCustomer.getRole());

            em.merge(existingCustomer);
        }
        return existingCustomer;
    }

    public Customer save (EntityManager em, Customer customer) {
        customer.getAddressCustomers().forEach(addressCustomer -> {
            System.out.println("AddressCustomer: id " + addressCustomer.getAddress().getCity().getId());
            System.out.println("AddressCustomer: cityname " + addressCustomer.getAddress().getCity().getCityName());
        });

        em.persist(customer);
        em.flush(); // Force the SQL to be executed now

        return em.find(Customer.class, customer.getId());
    }
}
