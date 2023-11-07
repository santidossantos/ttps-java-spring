package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.GroupCategoryDAO;
import ttps.java.grupo1.model.GroupCategory;

public class GroupCategoryDAOImplJPA extends GenericDAOImplJPA<GroupCategory> implements GroupCategoryDAO {

    public GroupCategoryDAOImplJPA(Class<GroupCategory> persistentClass) {
        super(persistentClass);
    }

}
