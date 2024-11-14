package be.neoo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityTransaction;
import be.neoo.repository.CustomerRepository;
import jakarta.persistence.EntityManager;
import be.neoo.dto.AddressCustomerDto;
import be.neoo.dto.CustomerDto;
import be.neoo.connection.EMF;
import be.neoo.dto.RoleDto;
import be.neoo.entities.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerService(CustomerRepository customerRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<Customer> findAll() {
        EntityManager em = EMF.getEM();
        return customerRepository.findAll(em);
    }

    public CustomerDto disableCustomer(int id){
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        RoleDto roleDto = new RoleDto();
        CustomerDto customerDto = new CustomerDto();

        try {
            trans.begin();
            Customer desactivatedCustomer = customerRepository.disableCustomer(em, id);
            trans.commit();

            customerDto.setId(desactivatedCustomer.getId());
            customerDto.setLogin(desactivatedCustomer.getLogin());
            customerDto.setLastName(desactivatedCustomer.getLastName());
            customerDto.setFirstName(desactivatedCustomer.getFirstName());
            customerDto.setBirthday(desactivatedCustomer.getBirthday());
            customerDto.setBirthday(desactivatedCustomer.getBirthday());
            customerDto.setVat(desactivatedCustomer.getVat());
            customerDto.setMail(desactivatedCustomer.getMail());
            customerDto.setPassword(desactivatedCustomer.getPassword());
            customerDto.setStatus(desactivatedCustomer.getStatus());
            customerDto.setActif(desactivatedCustomer.isActif());
            customerDto.setInscriptionDate(desactivatedCustomer.getInscriptionDate());

            roleDto = modelMapper.map(desactivatedCustomer.getRole(), RoleDto.class);
            customerDto.setRole(roleDto);

            List<AddressCustomerDto> addressCustomerDtoList = new ArrayList<>();

            desactivatedCustomer.getAddressCustomers().forEach(addressCustomerDto -> {
                addressCustomerDtoList.add(modelMapper.map(addressCustomerDto, AddressCustomerDto.class));
            });
            customerDto.getAddress().addAll(addressCustomerDtoList);


        }catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback();
            }
            log.error("Error disabling customer", e);
        } finally {
            // Fermeture de l'EntityManager dans le bloc finally
            if (em.isOpen()) {
                em.close();
            }
        }
        return customerDto;
    }

    public CustomerDto updateCustomer(int id, CustomerDto customerDto){
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        customerDto.setPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
        Customer updateCustomer = modelMapper.map(customerDto, Customer.class);
        RoleDto roleDto = new RoleDto();
        try {
            trans.begin();
            updateCustomer =customerRepository.updateCustomer(em , id , updateCustomer);
            trans.commit();

            customerDto.setId(updateCustomer.getId());
            customerDto.setLogin(updateCustomer.getLogin());
            customerDto.setFirstName(updateCustomer.getFirstName());
            customerDto.setLastName(updateCustomer.getLastName());
            customerDto.setBirthday(updateCustomer.getBirthday());
            customerDto.setInscriptionDate(updateCustomer.getInscriptionDate());
            customerDto.setVat(updateCustomer.getVat());
            customerDto.setMail(updateCustomer.getMail());
            customerDto.setPassword(updateCustomer.getPassword());
            customerDto.setStatus(updateCustomer.getStatus());
            customerDto.setActif(updateCustomer.isActif());

            roleDto.setId(updateCustomer.getRole().getId());
            roleDto.setRole(updateCustomer.getRole().getRole());

            customerDto.setRole(roleDto);

        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }

        return customerDto;

    }

    public Customer save(CustomerDto customerDto){
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        customerDto.setPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));

        Customer customerDB = modelMapper.map(customerDto, Customer.class);

        if (customerDto.getAddress() != null) {
            List<AddressCustomer> addressList = new ArrayList<>();

            for (AddressCustomerDto addressCustomerDto : customerDto.getAddress()) {
                if (addressCustomerDto.getAddress() != null && addressCustomerDto.getAddress().getCity() != null) {
                    AddressCustomer addressCustomer = modelMapper.map(addressCustomerDto, AddressCustomer.class);

                    // Map address and city details
                    addressCustomer.getAddress().setId(addressCustomerDto.getAddress().getId());
                    addressCustomer.getAddress().setBox(addressCustomerDto.getAddress().getBox());
                    addressCustomer.getAddress().setNumber(addressCustomerDto.getAddress().getNumber());
                    addressCustomer.getAddress().setStreet(addressCustomerDto.getAddress().getStreet());

                    // Ensure city and country objects are set correctly
                    City city = addressCustomer.getAddress().getCity();
                    city.setId(addressCustomerDto.getAddress().getCity().getId());
                    city.setCityName(addressCustomerDto.getAddress().getCity().getCityName());
                    city.setZipCode(addressCustomerDto.getAddress().getCity().getZipCode());

                    Country country = city.getCountry();
                    if (country != null) {
                        country.setId(addressCustomerDto.getAddress().getCity().getCountry().getId());
                        country.setName(addressCustomerDto.getAddress().getCity().getCountry().getName());
                    }

                    // Set customer reference to avoid null issues
                    addressCustomer.setCustomer(customerDB);
                    addressList.add(addressCustomer);
                }
            }

            // Add all addresses to customer
            customerDB.getAddressCustomers().addAll(addressList);
        }

        try {
            trans.begin();
            customerDB = customerRepository.save(em, customerDB);
            trans.commit();
        } catch (Exception e) {
            log.error("Error during save, rolling back transaction", e);
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return customerDB;
    }

}
