package ttps.java.grupo1.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {

    private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("persistence-unit");
    
    private EMF() {}

    public static EntityManagerFactory getEMF() {
        return em;
    }

}

