package ttps.java.grupo1.DAO;


import ttps.java.grupo1.model.Expense;

public interface ExpenseDAO extends GenericDAO<Expense>{
    public Double getAmount();
}
