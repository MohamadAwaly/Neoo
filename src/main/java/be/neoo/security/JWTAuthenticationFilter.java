package be.neoo.security;

import be.neoo.services.EmployeeService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import com.auth0.jwt.algorithms.Algorithm;
import be.neoo.repository.RoleRepository;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
import be.neoo.entities.Employee;
import be.neoo.connection.EMF;
import be.neoo.entities.Role;
import com.auth0.jwt.JWT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private EmployeeService employeeService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, RoleRepository roleRepository, EmployeeService employeeService) {
        super();
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.employeeService = employeeService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Employee employee = null;
        try {
            employee = new ObjectMapper().readValue(request.getInputStream(), Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employee.getLogin(), employee.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();

//        springUser.getAuthorities().forEach(role -> roles.add(role.getAuthority()));
        // Extraction des rôles et des permissions
        System.out.println("successfulAuthentication dans le if");
        System.out.println("successfulAuthentication dans le if");
        System.out.println("successfulAuthentication dans le if");
        System.out.println("successfulAuthentication dans le if");
        springUser.getAuthorities().forEach(authority -> {
            String authorityName = authority.getAuthority();
            roles.add(authorityName);
            // Ajout des permissions associées à chaque rôle
            Role role = getRoleByName(authorityName);
            role.getRolePermissions().forEach(permission -> permissions.add(permission.getPermission().getName()));
            if (authorityName.startsWith("ROLE_")) {

//                roles.add(authorityName);
//                // Ajout des permissions associées à chaque rôle
//                Role role = getRoleByName(authorityName);
//                role.getRolePermissions().forEach(permission -> permissions.add(permission.getPermission().getName()));
            } else {
                permissions.add(authorityName);
            }
        });
        String jwt = JWT.create()
                .withSubject(springUser.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withArrayClaim("permissions", permissions.toArray(new String[permissions.size()]))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecParams.EXP_TIME))
                .sign(Algorithm.HMAC256(SecParams.SECRET));

        // Create a cookie and add the JWT to it
//        Cookie cookie = new Cookie("JWT_TOKEN", jwt);
//        cookie.setHttpOnly(true); // Ensure the cookie is accessible only by the server
//        cookie.setSecure(true); // Ensure the cookie is sent only over HTTPS
//        cookie.setPath("/"); // Set the path for the cookie
//        cookie.setMaxAge((int) SecParams.EXP_TIME / 1000); // Set the expiration time for the cookie
//        response.addCookie(cookie);
        response.addHeader("Authorization", jwt);

//        System.out.println("JWT: " + jwt);
//        System.out.println("username :: " + springUser.getUsername());

//        EmployeeConnexionDto employee = new EmployeeConnexionDto();
//        employee.setFistName(springUser.getUsername());
//        Employee employee = employeeService.findByLogin(springUser.getUsername());
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(employee));
    }

    // Méthode pour obtenir les rôles et les permissions
    private Role getRoleByName(String roleName) {
        // Implémentez cette méthode pour récupérer les rôles de l'utilisateur depuis la base de données
        // Par exemple, une méthode du repository peut être appelée ici
        EntityManager em = EMF.getEM();
        return roleRepository.findByName(em, roleName); // Exemple
    }
}
