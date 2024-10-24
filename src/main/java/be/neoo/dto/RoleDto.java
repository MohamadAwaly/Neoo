package be.neoo.dto;

import be.neoo.entities.RolePermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private int id;
    private String role;
    private Set<RolePermission> rolePermissions;
}
