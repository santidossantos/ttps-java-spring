package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.RecurrentExpenseDAO;
import ttps.java.grupo1.model.RecurrentExpense;

public class RecurrentExpenseDAOImplJPA extends GenericDAOImplJPA<RecurrentExpense> implements RecurrentExpenseDAO {

    public RecurrentExpenseDAOImplJPA(Class<RecurrentExpense> persistentClass) {
        super(persistentClass);
    }
}
