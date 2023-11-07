package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.ExpenseStrategyDAO;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;

public class ExpenseStrategyDAOImplJPA extends GenericDAOImplJPA<ExpenseStrategy> implements ExpenseStrategyDAO {

    public ExpenseStrategyDAOImplJPA(Class<ExpenseStrategy> persistentClass) {
        super(ExpenseStrategy.class);
    }

}
