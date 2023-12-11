package ttps.java.grupo1.model.strategy;


import jakarta.persistence.Entity;

@Entity
public class EqualAmountsPercent extends ExpenseStrategy {

    public double calculateAmount(){
        return 2;
    }
}
