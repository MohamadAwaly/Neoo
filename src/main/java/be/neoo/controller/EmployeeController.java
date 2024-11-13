package be.neoo.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.StringUtils;
import be.neoo.services.EmployeeService;
import be.neoo.entities.Employee;
import org.slf4j.LoggerFactory;
import be.neoo.dto.EmployeeDto;
import org.slf4j.Logger;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employe) {
        Employee employee = employeeService.save(employe);
        return employee;
    }


    @GetMapping("/display")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EmployeeDto> getAllEmployees() {
        log.info("getAllEmployees");
        return employeeService.findAll();
    }

    @GetMapping("/currentUser")
    public Employee currentUser() {
        String login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee employee = employeeService.findByLogin(login);
        return employee;
    }

//@PreAuthorize("hasAuthority('ROLE_ADMIN')")

    @PutMapping("/updateUser/{id}")
    public EmployeeDto updateEmploye(@PathVariable int id, @RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmploye(id, employeeDto);
    }

    @PutMapping("/disableEmployee/{id}")
    public EmployeeDto disableEmployee(@PathVariable int id) {
        return employeeService.disableEmployee(id);
    }

    @GetMapping("/checkIfLoginExist")
    public boolean checkIfLoginExist(@RequestParam String login){
        return employeeService.checkIfLoginExist(login);
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        boolean isSecure = false;
        String contextPath = null;
        if (request != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            isSecure = request.isSecure();
            contextPath = request.getContextPath();
        }
        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContextHolder.clearContext();
        context.setAuthentication(null);
        if (response != null) {
            Cookie cookie = new Cookie("JSESSIONID", null);
            String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            cookie.setSecure(isSecure);
            response.addCookie(cookie);
        }
    }
}
