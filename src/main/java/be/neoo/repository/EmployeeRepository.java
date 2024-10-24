package be.neoo.repository;

import be.neoo.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private static final Logger log = LoggerFactory.getLogger(EmployeeRepository.class);

    public Employee save(EntityManager em, Employee employee) {
        em.persist(employee);
        return em.find(Employee.class, employee.getId());
    }

    public List<Employee> findAll(EntityManager em) {
        Query query = em.createNamedQuery("employe.findAll", Employee.class);
        return query.getResultList();
    }
    public Employee findByLogin(EntityManager em, String login) {
        Query query = em.createNamedQuery("employe.findByLogin", Employee.class);
        query.setParameter("login", login);
        return (Employee) query.getSingleResult();
    }

    public Employee updateEmploye (EntityManager em,int id, Employee updatedEmployee) {
        // Trouver l'entité existante dans la base de données
        Employee existingEmployee = em.find(Employee.class, id);
        if (existingEmployee != null) {
            // Mettre à jour les champs nécessaires
            existingEmployee.setLogin(updatedEmployee.getLogin());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setBirthday(updatedEmployee.getBirthday());
            existingEmployee.setInscriptionDate(updatedEmployee.getInscriptionDate());
            existingEmployee.setVat(updatedEmployee.getVat());
            existingEmployee.setMail(updatedEmployee.getMail());
            existingEmployee.setStatus(updatedEmployee.getStatus());
            existingEmployee.setActif(updatedEmployee.isActif());
            existingEmployee.setRole(updatedEmployee.getRole());
            // Fusionner l'entité mise à jour dans le contexte de persistance
            em.merge(existingEmployee);
        }
        return existingEmployee;

    }

    public Employee disableEmployee(EntityManager em, int id) {
        Employee employee = em.find(Employee.class, id);
        if (employee != null) {
            employee.setActif(false);
            em.merge(employee);
        }
        return employee;
    }



}
