package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.ExpenseDAO;
import ttps.java.grupo1.model.Expense;

public class ExpenseDAOImplJPA extends GenericDAOImplJPA<Expense> implements ExpenseDAO {

    public ExpenseDAOImplJPA(Class<Expense> persistentClass) {
        super(persistentClass);
    }

    public Double getAmount(){
        return 0.0;
    }

}
