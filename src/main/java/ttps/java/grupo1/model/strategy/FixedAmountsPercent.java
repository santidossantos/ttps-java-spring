package ttps.java.grupo1.model.strategy;


import jakarta.persistence.Entity;

@Entity
public class FixedAmountsPercent extends ExpenseStrategy {

    public double calculateAmount(){
        return 4;
    }
}
