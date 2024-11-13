package be.neoo.dto;

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
    private Set<RolePermissionDto> rolePermissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<RolePermissionDto> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermissionDto> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
