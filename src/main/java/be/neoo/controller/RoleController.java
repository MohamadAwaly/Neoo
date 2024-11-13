package be.neoo.controller;


import be.neoo.dto.BrandDto;
import be.neoo.dto.RoleDto;
import be.neoo.entities.Employee;
import be.neoo.entities.Role;
import be.neoo.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/roles")
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    RoleService roleService;

    //    Constructor
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/roles")
    public List<RoleDto> findAllRoles() {
        return roleService.findAllRoles();
    }

    @PutMapping("/update/{id}")
    public RoleDto update(@PathVariable int id, @RequestBody RoleDto roleDto) {
        return roleService.update(id, roleDto);
    }

    @PostMapping("/save")
    public RoleDto save(@RequestBody RoleDto roleDto) {
        roleService.save(roleDto);
        return roleDto;
    }

}
