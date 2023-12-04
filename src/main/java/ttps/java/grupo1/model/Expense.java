package ttps.java.grupo1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column
    private Double amount;

    @Column
    private String name;

    @Column
    private Date date;

    @Column
    private String img;

    @ManyToOne
    @JoinColumn(name="group_id")
    @JsonBackReference
    private Group group;

    @ManyToOne
    private ExpenseCategory category;

    @ManyToOne
    private User payingUser;

    @OneToMany
    private List<ExpenseUsersPays> debtorsUsers;

    @ManyToOne
    private ExpenseStrategy expenseStrategy;

    public Expense(Double amount, String name, Date date, String img, Group group, ExpenseCategory category, User payingUser, List<ExpenseUsersPays> debtorsUsers, ExpenseStrategy expenseStrategy) {
        this.amount = amount;
        this.name = name;
        this.date = date;
        this.img = img;
        this.group = group;
        this.category = category;
        this.payingUser = payingUser;
        this.debtorsUsers = debtorsUsers;
        this.expenseStrategy = expenseStrategy;
    }

    public Expense(Double amount, String name, Date date, String img, Group group, ExpenseCategory category, User payingUser, ExpenseStrategy expenseStrategy) {
        this.amount = amount;
        this.name = name;
        this.date = date;
        this.img = img;
        this.group = group;
        this.category = category;
        this.payingUser = payingUser;
        this.expenseStrategy = expenseStrategy;
    }

}
