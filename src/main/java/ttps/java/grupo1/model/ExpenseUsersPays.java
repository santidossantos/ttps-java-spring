package ttps.java.grupo1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.*;

@Entity
@Table(name = "`expense_users_pays`")
@NoArgsConstructor
@Data
public class ExpenseUsersPays {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private Double amountPayed;

    @Column
    private Boolean isPayed;

    @ManyToOne
    private User user;

    public ExpenseUsersPays(Double amountPayed, Boolean isPayed, User user) {
        this.amountPayed = amountPayed;
        this.isPayed = isPayed;
        this.user = user;
    }
}
