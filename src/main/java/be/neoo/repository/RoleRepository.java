package be.neoo.repository;

import be.neoo.entities.Employee;
import be.neoo.entities.Permission;
import be.neoo.entities.Role;
import be.neoo.entities.RolePermission;
import be.neoo.services.RoleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleRepository {

    private static final Logger log = LoggerFactory.getLogger(RoleRepository.class);


    /**
     * find role by role
     * @param em
     * @param role
     * @return
     */
    public Role findByName(EntityManager em, String role) {
        Query query = em.createNamedQuery("Role.findByName", Role.class);
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    /**
     * get List of roles with permission list
     * @param em
     * @return
     */
    public List<Role> findAllRoles(EntityManager em) {
        Query query = em.createNamedQuery("Role.findAll", Role.class);
        return query.getResultList();
    }

    /**
     * update role and his permission list
     * @param em
     * @param id
     * @param role
     * @return
     */
    public Role update (EntityManager em,int id, Role role) {
        Role oldRole = em.find(Role.class, id);

        oldRole.setRole(role.getRole());

        Set<RolePermission> oldPermissions = new HashSet<>(oldRole.getRolePermissions());

        Set<RolePermission> newPermissions = new HashSet<>(role.getRolePermissions());

        // Remove permissions that are no longer present
        oldPermissions.stream()
                .filter(permission -> !newPermissions.contains(permission))
                .forEach(permission -> oldRole.getRolePermissions().remove(permission));

        // Add new permissions that are missing in the old role
        newPermissions.stream()
                .filter(permission -> !oldPermissions.contains(permission))
                .forEach(permission -> {
                    permission.setRole(oldRole); // Set the role in RolePermission
                    oldRole.getRolePermissions().add(permission);
                });

        // Merge changes
        em.merge(oldRole);
        em.flush();
        return oldRole;
    }

    /**
     * add new role
     * @param em
     * @param role
     * @return
     */
    public Role save(EntityManager em, Role role) {
        for (RolePermission rolePermission : role.getRolePermissions()) {
            Permission managedPermission = em.find(Permission.class, rolePermission.getPermission().getId());
            if (managedPermission == null) {
                throw new IllegalArgumentException("Permission with ID " + rolePermission.getPermission().getId() + " does not exist.");
            }
            rolePermission.setPermission(managedPermission);
            rolePermission.setRole(role);
        }
        Role managedRole = em.merge(role);
        em.flush();

        return managedRole;
    }

}
