package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.dto.AddressDto;
import be.neoo.repository.AddressRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService {

    private AddressRepository addressRepository;

    public List<AddressDto> findAll (){
        EntityManager em = EMF.getEM();
        addressRepository.findAll(em);
        return  null;
    }
}
