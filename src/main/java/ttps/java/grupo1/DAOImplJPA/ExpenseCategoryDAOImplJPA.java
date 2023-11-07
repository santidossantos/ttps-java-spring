package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.ExpenseCategoryDAO;
import ttps.java.grupo1.model.ExpenseCategory;

public class ExpenseCategoryDAOImplJPA extends GenericDAOImplJPA<ExpenseCategory> implements ExpenseCategoryDAO {

    public ExpenseCategoryDAOImplJPA(Class<ExpenseCategory> persistentClass) {
        super(persistentClass);
    }
}
