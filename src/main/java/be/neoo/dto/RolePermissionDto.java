package be.neoo.dto;

import be.neoo.entities.Permission;
import be.neoo.entities.Role;


public class RolePermissionDto {
    private Role role;
    private Permission permission;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
