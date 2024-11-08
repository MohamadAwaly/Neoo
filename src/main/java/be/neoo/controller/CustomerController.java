package be.neoo.controller;

import be.neoo.entities.Customer;
import be.neoo.entities.Employee;
import be.neoo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/display")
    public List<Customer> getAllEmployees() {
        return customerService.findAll();
    }


}
