package be.neoo.connection;


import jakarta.persistence.*;

/**
 * Class to get a connection to the database
 * 
 * @author Renaud DIANA
 */
public final class EMF {
	
	private static final EntityManagerFactory emfInstance =
	        Persistence.createEntityManagerFactory("TestSpring");

    private EMF() {}

    public static EntityManagerFactory getEMF() {
        return emfInstance;
    }
    
    public static EntityManager getEM() {
        return emfInstance.createEntityManager();
    }
 
 /*	Create EntityManager in others classes
  * EntityManager em = EMF.get().createEntityManager();
  * try {
  *     // ... do stuff with em ...
  * } finally {
  *     em.close();
  * }
  */
}
