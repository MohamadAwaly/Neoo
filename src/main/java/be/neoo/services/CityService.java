package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.controller.EmployeeController;
import be.neoo.dto.CityDto;
import be.neoo.entities.City;
import be.neoo.repository.CityRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {


    private final CityRepository cityRepository;
    private ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(CityService.class);

    /**
     * Constructor
     * @param cityRepository
     */
    public CityService(CityRepository cityRepository, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
    }

    public List<CityDto> getCities() {
        EntityManager em = EMF.getEM();
        List<City> cities = new ArrayList<>();
        List<CityDto> citiesDto = new ArrayList<>();
        cities = cityRepository.getCities(em);
        cities.forEach(city -> citiesDto.add(modelMapper.map(city, CityDto.class)));

        return citiesDto;
    }
}
