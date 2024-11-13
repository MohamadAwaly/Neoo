package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.dto.PermissionDto;
import be.neoo.entities.Permission;
import be.neoo.repository.PermissionRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    private PermissionRepository permissionRepository;
    private ModelMapper modelMapper;


    public PermissionService(ModelMapper modelMapper, PermissionRepository permissionRepository) {
        this.modelMapper = modelMapper;
        this.permissionRepository = permissionRepository;
    }

    public List<PermissionDto> getPermissions (){
        EntityManager em = EMF.getEM();
        List<Permission> permissions = new ArrayList<>();
        List<PermissionDto> permissionDtos = new ArrayList<>();
        permissions = permissionRepository.getPermissions(em);
        permissions.forEach(permission -> permissionDtos.add(modelMapper.map(permission, PermissionDto.class)));
        return permissionDtos;

    }
}
