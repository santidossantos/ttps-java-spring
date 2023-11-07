package ttps.java.grupo1.DAOImplJPA;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import lombok.Data;
import ttps.java.grupo1.DAO.GenericDAO;
import ttps.java.grupo1.utils.EMF;

import java.io.Serializable;
import java.util.List;

@Data
public class GenericDAOImplJPA<T> implements GenericDAO<T> {

    private EntityManager em;
    private EntityTransaction tx;
    private Class<T> persistentClass;

    public GenericDAOImplJPA() {
        this.em = EMF.getEMF().createEntityManager();
    }

    public GenericDAOImplJPA(Class<T> persistentClass) {
        this();
        this.persistentClass = persistentClass;
    }

    private void beginTransaction() {
        tx = em.getTransaction();
        tx.begin();
    }

    public T save(T entity) {
        this.beginTransaction();
        this.em.persist(entity);
        this.tx.commit();
        return entity;
    }

    public T get(Serializable id) {
        return em.find(getPersistentClass(), id);
    }

    public List<T> getAll(String column) {
        Query query = this.getEm().createQuery("from "+ getPersistentClass().getSimpleName());
        return (List<T>) query.getResultList();
    }

    public T update(T entity) {
        return this.save(entity);
    }

    public boolean exists(Long id) {
        return em.find(getPersistentClass(), id) != null;
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public boolean delete(Long id) {
        T entity = this.get(id);
        this.beginTransaction();
        if(entity != null) {
            this.delete(entity);
            this.tx.commit();
            return true;
        }
        return false;
    }

    public void deleteAll() {
        this.beginTransaction();
        Query query = this.getEm().createQuery("DELETE FROM "+ getPersistentClass().getSimpleName());
        query.executeUpdate();
        this.tx.commit();
    }

}
