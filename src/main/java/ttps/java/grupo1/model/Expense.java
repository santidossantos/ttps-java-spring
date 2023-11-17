package ttps.java.grupo1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;

import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "`expense`")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="expense_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@Data
public class Expense {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column
    private String name;

    @Column
    private Date date;

    @Column
    private String img;

    @ManyToOne
    @JoinColumn(name="group_id", nullable = false)
    private Group group;

    @OneToOne
    @JoinColumn(name="category_id")
    private ExpenseCategory category;

    @OneToOne
    private User payingUser;

    @OneToMany
    private List<ExpenseUsersPays> debtorsUsers;

    @ManyToOne
    private ExpenseStrategy expenseStrategy;

    public Expense(Double amount, Date date, String img, Group group, ExpenseCategory category, User payingUser, List<ExpenseUsersPays> debtorsUsers, ExpenseStrategy expenseStrategy) {
        this.amount = amount;
        this.date = date;
        this.img = img;
        this.group = group;
        this.category = category;
        this.payingUser = payingUser;
        this.debtorsUsers = debtorsUsers;
        this.expenseStrategy = expenseStrategy;
    }

}
