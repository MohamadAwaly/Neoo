package be.neoo.controller;


import be.neoo.dto.RoleDto;
import be.neoo.entities.Employee;
import be.neoo.entities.Role;
import be.neoo.services.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/roles")
public class RoleController {

    RoleService roleService;

//    Constructor
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<RoleDto> findAllRoles() {
        return roleService.findAllRoles();
    }

}
