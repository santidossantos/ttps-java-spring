package ttps.java.grupo1.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.ExpenseCategory;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.service.ExpenseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Component
public class ExpenseSeeder {

    @Autowired
    ExpenseService expenseService;

    private ExpenseCategory categoryTravel = new ExpenseCategory("Viaje", "icon.png");;
    private Group group;
    private Date today;

    public void runExpenseSeeder(){

//        expenseService.deleteAll();
//
//        //Create group
//        List<User> users = new ArrayList<>();
//        group = new Group("grupo1", false, users);
//
//        //Set the day
//        today = Date.from(Instant.now());
//
//        User payingUser = new User("Jorge", "Rosso", "1234", "jorge@gmail.com");
//        Expense expense = new Expense(100.0, today, "image.png", group, categoryTravel, payingUser, null, null);
//
//        expenseService.saveExpense(expense);
    }
}
