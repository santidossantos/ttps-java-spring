package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.UserDAO;
import ttps.java.grupo1.model.User;

public class UserDAOImplJPA extends GenericDAOImplJPA<User> implements UserDAO {

    public UserDAOImplJPA(Class<User> persistentClass) {
        super(persistentClass);
    }
}