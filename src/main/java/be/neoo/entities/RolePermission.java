package be.neoo.entities;



import be.neoo.entities.compoundID.RolePermissionPK;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass( RolePermissionPK.class )
@Table(name = "roles_permissions")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn( name = "role", nullable = false )
    @JsonBackReference
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn( name = "permission", nullable = false )
    private Permission permission;

    public Role getRole() {
        return role;
    }

    public void setRole( Role role ) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission( Permission permission ) {
        this.permission = permission;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        RolePermission that = (RolePermission) o;
        return Objects.equals( role, that.role ) && Objects.equals( permission, that.permission );
    }

    @Override public int hashCode() {
        return Objects.hash( role, permission );
    }
}
