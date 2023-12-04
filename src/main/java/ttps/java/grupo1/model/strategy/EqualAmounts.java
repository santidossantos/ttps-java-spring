package ttps.java.grupo1.model.strategy;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class EqualAmounts extends ExpenseStrategy {

    public double calculateAmount(){
        return 1;
    }

}
