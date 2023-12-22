package ttps.java.grupo1.model.strategy;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "expense_strategy")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="expense_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "expenseStrategy",
        use = JsonTypeInfo.Id.NAME,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EqualAmounts.class, name = "EqualAmounts"),
        @JsonSubTypes.Type(value = FixedAmountsPercent.class, name = "FixedAmountsPercent"),
        @JsonSubTypes.Type(value = FixedAmounts.class, name = "FixedAmounts")
})
public abstract class ExpenseStrategy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    public abstract double calculateAmount();

}
