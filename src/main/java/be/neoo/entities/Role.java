package be.neoo.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@NamedQueries(value = {
        @NamedQuery(name = "Role.findAll",
                query = "select r from Role r "),
        @NamedQuery(name = "getRoleByID",
                query = "select r from Role r where r.id = :id"),
        @NamedQuery(name = "Role.findByName",
                query = "select r from Role r where r.role = :role")
})


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "role", nullable = false, length = 60)
    private String role;

    @OneToMany( mappedBy = "role", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER )
    @JsonManagedReference
    private Set<RolePermission> rolePermissions;

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

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
