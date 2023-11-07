package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.*;
import ttps.java.grupo1.model.*;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;

public class DAOFactory {

    public static UserDAO getUserDAO() {
        return new UserDAOImplJPA(User.class);
    }

    public static GroupDAO getGroupDAO() {
        return new GroupDAOImplJPA(Group.class);
    }

    public static GroupCategoryDAO getGroupCategoryDAO() {
        return new GroupCategoryDAOImplJPA(GroupCategory.class);
    }

    public static ExpenseDAO getExpenseDAO() {
        return new ExpenseDAOImplJPA(Expense.class);
    }

    public static RecurrentExpenseDAO getRecurrentExpenseDAO(){
        return new RecurrentExpenseDAOImplJPA(RecurrentExpense.class);
    }

    public static ExpenseUsersPaysDAO getExpenseUsersPaysDAO(){
        return new ExpenseUsersPaysDAOImplJPA(ExpenseUsersPays.class);
    }

    public static ExpenseCategoryDAO getExpenseCategoryDAO(){
        return new ExpenseCategoryDAOImplJPA(ExpenseCategory.class);
    }

    public static ExpenseStrategyDAO getExpenseStrategyDAO(){
        return new ExpenseStrategyDAOImplJPA(ExpenseStrategy.class);
    }

}
