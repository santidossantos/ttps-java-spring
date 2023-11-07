package ttps.java.grupo1.DAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {

    T save(T entity);

    T get(Serializable id);

    List<T> getAll(String column);

    T update(T entity);

    boolean exists(Long id);

    void delete(T entity);

    boolean delete(Long id);

    void deleteAll();
}
