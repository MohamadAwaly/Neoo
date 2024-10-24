package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.dto.AddressDto;
import be.neoo.dto.AddressEmployeDto;
import be.neoo.dto.EmployeeDto;
import be.neoo.dto.RoleDto;
import be.neoo.entities.Employee;
import be.neoo.entities.Role;
import be.neoo.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    EmployeeRepository employeeRepository;
    ModelMapper modelMapper;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    public EmployeeDto save(EmployeeDto employeeDto) {
//        EntityManager em = EMF.getEM();
//        EntityTransaction trans = em.getTransaction();
//        EmployeeDto employeeDto1 = new EmployeeDto();
//        Employee employee = modelMapper.map(employeeDto, Employee.class);
//        Role role = modelMapper.map(employee.getRole(), Role.class);
//        employee.setRole(role);
//        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
//        try {
//            trans.begin();
//            employeeDto1 = modelMapper.map(employeeRepository.save(em, employee), EmployeeDto.class);
//            trans.commit();
//        } catch (Exception e) {
//            trans.rollback();
//        } finally {
//            if (trans.isActive()) {
//                trans.rollback();
//            }
//            em.close();
//        }
//        return employeeDto1;
//    }

    public Employee save(Employee employee) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Employee employeeBD = new Employee();
//        Role role = modelMapper.map(employee.getRole(), Role.class);
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        try {
            trans.begin();
            employeeBD = employeeRepository.save(em, employee);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return employeeBD;
    }

    public List<EmployeeDto> findAll() {
        EntityManager em = EMF.getEM();
        List<Employee> employees = employeeRepository.findAll(em);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        employees.forEach(employee -> {
            // Mapper l'objet Employee en EmployeeDto
            EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
            // Mapper l'objet Role en RoleDto
            RoleDto roleDto = modelMapper.map(employee.getRole(), RoleDto.class);
            employeeDto.setRoleDto(roleDto);

            employeeDtos.add(employeeDto);

            });
        return employeeDtos;
    }

    public Employee findByLogin(String login) {
        EntityManager em = EMF.getEM();
        return employeeRepository.findByLogin(em, login);
    }

    public EmployeeDto updateEmploye(int id, EmployeeDto employeeDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        employeeDto.setPassword(bCryptPasswordEncoder.encode(employeeDto.getPassword()));
        Employee updateEmployee = modelMapper.map(employeeDto, Employee.class);
        RoleDto roleDto = new RoleDto();
        try {
            trans.begin();
            updateEmployee = employeeRepository.updateEmploye(em, id, updateEmployee);
            trans.commit();
//            employeeDto = modelMapper.map(updateEmployee, EmployeeDto.class);
            employeeDto.setId(updateEmployee.getId());
            employeeDto.setLogin(updateEmployee.getLogin());
            employeeDto.setFirstName(updateEmployee.getFirstName());
            employeeDto.setLastName(updateEmployee.getLastName());
            employeeDto.setBirthday(updateEmployee.getBirthday());
            employeeDto.setInscriptionDate(updateEmployee.getInscriptionDate());
            employeeDto.setVat(updateEmployee.getVat());
            employeeDto.setMail(updateEmployee.getMail());
            employeeDto.setPassword(updateEmployee.getPassword());
            employeeDto.setStatus(updateEmployee.getStatus());
            employeeDto.setActif(updateEmployee.isActif());
            roleDto.setId(updateEmployee.getRole().getId());
            roleDto.setRole(updateEmployee.getRole().getRole());
            employeeDto.setRoleDto(roleDto);
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }

        return employeeDto;
    }

    public EmployeeDto disableEmployee(int id) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Employee disactivatedEmployee = new Employee();
        RoleDto roleDto = new RoleDto();
        EmployeeDto employeeDto = new EmployeeDto();
        try {
            trans.begin();
            disactivatedEmployee = employeeRepository.disableEmployee(em, id);
            trans.commit();
            roleDto.setId(disactivatedEmployee.getRole().getId());
            roleDto.setRole(disactivatedEmployee.getRole().getRole());
            employeeDto = modelMapper.map(disactivatedEmployee, EmployeeDto.class);
            employeeDto.setRoleDto(roleDto);
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return employeeDto;
    }
}
