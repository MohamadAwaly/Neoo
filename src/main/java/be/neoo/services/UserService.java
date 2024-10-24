package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.entities.User;
import be.neoo.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Add new user
     */
    public void addUser(User user) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        try {
            trans.begin();
            userRepository.addUser(em, user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
    }

    public List<User> getAllUsers() {
        EntityManager em = EMF.getEM();
        List<User> users = new ArrayList<>();
        return users = userRepository.getAllUsers(em);
    }

    public User updateUser(User user) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            userRepository.updateUser(em, user);
            trans.commit();
            return user;
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
        }
        return user;
    }

    public void deleteUser(int id) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            userRepository.deleteUser(em, id);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
        }
    }
}
