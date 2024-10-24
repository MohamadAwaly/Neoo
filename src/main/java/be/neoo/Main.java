package be.neoo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    private static final String               PERSISTENCE_UNIT_NAME = "TestSpring";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {


        factory = Persistence.createEntityManagerFactory( PERSISTENCE_UNIT_NAME );
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
    }


}
