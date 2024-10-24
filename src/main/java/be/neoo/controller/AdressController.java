package be.neoo.controller;


import be.neoo.dto.AddressDto;
import be.neoo.services.AdressService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/adress")
public class AdressController {

    private AdressService adressService;

    public List<AddressDto> findAll(){
       return adressService.findAll();
    }
}
