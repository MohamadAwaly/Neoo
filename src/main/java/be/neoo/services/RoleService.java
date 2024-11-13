package be.neoo.services;


import org.springframework.stereotype.Service;
import jakarta.persistence.EntityTransaction;
import be.neoo.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import be.neoo.connection.EMF;
import be.neoo.entities.Role;
import be.neoo.dto.RoleDto;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    RoleRepository roleRepository;
    ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * get all roles
     * @return
     */
    public List<RoleDto> findAllRoles() {
        EntityManager em = EMF.getEM();
        List<Role> roles = roleRepository.findAllRoles(em);
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(modelMapper.map(role, RoleDto.class));
        }
        return roleDtos;
    }

    /**
     * update role and his permission list
     * @param id
     * @param roleDto
     * @return
     */
    public RoleDto update(int id , RoleDto roleDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Role role = modelMapper.map(roleDto, Role.class);
        try {
            trans.begin();
            role = roleRepository.update(em, id , role);
            trans.commit();
            roleDto = modelMapper.map(role, RoleDto.class);
        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }

        return roleDto;
    }

    public RoleDto save(RoleDto roleDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        Role role = modelMapper.map(roleDto, Role.class);
        try{
            trans.begin();
            role = roleRepository.save(em, role);
            trans.commit();
            roleDto = modelMapper.map(role, RoleDto.class);
        }catch (Exception e) {
            log.error("Erreur /:::: ::: :: " + e.getMessage());
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return roleDto;
    }

}
