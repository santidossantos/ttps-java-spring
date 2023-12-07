package ttps.java.grupo1.DTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import ttps.java.grupo1.model.ExpenseCategory;
import ttps.java.grupo1.model.ExpenseUsersPays;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;

import java.util.Date;
import java.util.List;

@Data
public class AddExpenseDTO {

    @NotEmpty
    private String name;

    @NotNull
    private Double amount;

    @Past
    private Date date;

    @NotEmpty
    private String img;

    @NotNull
    private Group group;

    @NotNull
    private ExpenseCategory category;

    @NotNull
    private User payingUser;

    @NotNull
    private ExpenseStrategy expenseStrategy;

    private List<ExpenseUsersPays> debtorsUsers;

}