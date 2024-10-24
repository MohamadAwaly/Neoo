package be.neoo.security;

import be.neoo.entities.Employee;
import be.neoo.services.EmployeeService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    EmployeeService employeeService;

    public MyUserDetailsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeService.findByLogin(login);
        //Test if user exist
        if (employee == null) throw new UsernameNotFoundException("Utilisateur introuvable");
        List<GrantedAuthority> auths = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(employee.getRole().getRole());
        auths.add(authority);
        return new org.springframework.security.core.userdetails.User(employee.getLogin(), employee.getPassword(), auths);
    }
}
