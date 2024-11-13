package be.neoo.controller;

import be.neoo.dto.BrandDto;
import be.neoo.dto.PermissionDto;
import be.neoo.services.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/permissions")
public class PermissionController {
    private static final Logger log = LoggerFactory.getLogger(PermissionController.class);

    private PermissionService permissionSerice;

    public PermissionController(PermissionService permissionSerice) {
        this.permissionSerice = permissionSerice;
    }

    @GetMapping("/getPermissions")
    public List<PermissionDto> getPermissions() {
        return permissionSerice.getPermissions();
    }
}
