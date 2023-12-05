package ttps.java.grupo1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
public class RecurrentExpense extends Expense{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private Integer period;

    public RecurrentExpense(Double amount, String name, Date date, String img, Group group, ExpenseCategory category, User payingUser, List<ExpenseUsersPays> debtorsUsers, ExpenseStrategy expenseStrategy, Integer period) {
        super(amount, name, date, img, group, category, payingUser, debtorsUsers, expenseStrategy);
        this.period = period;
    }
}
