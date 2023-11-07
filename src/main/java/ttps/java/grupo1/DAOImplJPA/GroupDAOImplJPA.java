package ttps.java.grupo1.DAOImplJPA;


import ttps.java.grupo1.DAO.GroupDAO;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;

public class GroupDAOImplJPA extends GenericDAOImplJPA<Group> implements GroupDAO {

    public GroupDAOImplJPA(Class<Group> persistentClass) {
        super(persistentClass);
    }

    @Override
    public User addMember(User user) {
        return null;
    }

    @Override
    public void addExpense(Group group, double amount, String image) {
        return;
    }

    @Override
    public void addExpense(Group group, double amount, String image, double period) {
        return;
    }

    @Override
    public double calculateTotal() {
        return 0;
    }

    @Override
    public double getBalance() {
        return 0;
    }

}
