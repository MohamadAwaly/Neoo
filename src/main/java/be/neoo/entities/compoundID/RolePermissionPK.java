package be.neoo.entities.compoundID;

import java.io.Serializable;

public class RolePermissionPK implements Serializable {

    private static final long serialVersionUID = 1L;

    private int role;
    private int permission;

    public RolePermissionPK() {}

    public RolePermissionPK( int role, int permission ) {
        this.role = role;
        this.permission = permission;
    }

    public int getRole() {
        return role;
    }

    public void setRole( int role ) {
        this.role = role;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission( int permission ) {
        this.permission = permission;
    }

    public int getGetPermission() {
        return permission;
    }

    public void setGetPermission( int getPermission ) {
        this.permission = getPermission;
    }
}
