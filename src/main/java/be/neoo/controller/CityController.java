package be.neoo.controller;


import be.neoo.dto.CityDto;
import be.neoo.repository.EmployeeRepository;
import be.neoo.services.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/cities")
public class CityController {

    CityService cityService;
    private static final Logger log = LoggerFactory.getLogger(CityController.class);


    /**
     * Constructor
     * @param cityService
     */
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @GetMapping("/display")
    public List<CityDto> getCities() {
        return this.cityService.getCities();
    }
}
