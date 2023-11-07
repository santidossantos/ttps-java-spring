package ttps.java.grupo1.DAO;


import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;

public interface GroupDAO extends GenericDAO<Group> {

    public User addMember(User user);

    public void addExpense(Group group, double amount, String image);

    public void addExpense(Group group, double amount, String image, double period);

    public double calculateTotal();

    public double getBalance();

}
