package be.neoo.repository;

import be.neoo.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    public void addUser (EntityManager em, User user) {
        em.persist(user);
    }

    public List<User> getAllUsers (EntityManager em) {
        Query query = em.createNamedQuery("User.findAll");
        List<User> users = query.getResultList();
        return users;
    }

    public User updateUser (EntityManager em, User user) {
        return em.merge(user);
    }

    public void deleteUser (EntityManager em, int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

}
