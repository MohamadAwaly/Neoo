package be.neoo.services;


import be.neoo.connection.EMF;
import be.neoo.dto.RoleDto;
import be.neoo.entities.Employee;
import be.neoo.entities.Role;
import be.neoo.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {


    RoleRepository roleRepository;
    ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    public List<RoleDto> findAllRoles() {
        EntityManager em = EMF.getEM();
        List<Role> roles = roleRepository.findAllRoles(em);
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(modelMapper.map(role, RoleDto.class));
        }
        return roleDtos;
    }

}
