package ttps.java.grupo1.model.strategy;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "expense_strategy")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="expense_type", discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class ExpenseStrategy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

}
