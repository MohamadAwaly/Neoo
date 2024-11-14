package be.neoo.controller;

import be.neoo.dto.CustomerDto;
import be.neoo.entities.Customer;
import be.neoo.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/display")
    public List<Customer> getAllEmployees() {
        return customerService.findAll();
    }

    @PutMapping("/disableCustomer/{id}")
    public CustomerDto disableEmployee(@PathVariable int id) {
        return customerService.disableCustomer(id);
    }

    @PutMapping("/updateCustomer/{id}")
    public CustomerDto updateEmploye(@PathVariable int id, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(id, customerDto);
    }

    @PostMapping("/save")
    public CustomerDto save(@RequestBody CustomerDto customerDto) {

        customerDto.getAddress().forEach(adress ->{
            log.info("City Name :: " + adress.getAddress().getCity().getId());
            log.info("City Name :: " + adress.getAddress().getCity().getZipCode());
            log.info("City Name :: " + adress.getAddress().getCity().getCountry().getName());
            log.info("City Name :: " + adress.getAddress().getCity().getCityName());
        });

        customerService.save(customerDto);

        return customerDto;
//        return customerService.save(customer);
    }



}
