package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.ExpenseUsersPaysDAO;
import ttps.java.grupo1.model.ExpenseUsersPays;

public class ExpenseUsersPaysDAOImplJPA extends GenericDAOImplJPA<ExpenseUsersPays> implements ExpenseUsersPaysDAO {

    public ExpenseUsersPaysDAOImplJPA(Class<ExpenseUsersPays> persistentClass) {
        super(persistentClass);
    }

}
