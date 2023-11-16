package ttps.java.grupo1.seeders;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DataBaseSeeder {

    @EventListener(ApplicationReadyEvent.class)
    public void seedDatabase() {

            ExpenseSeeder expenseSeeder = new ExpenseSeeder();

            expenseSeeder.runExpenseSeeder();

            System.out.println("Database seeded successfully.");
        }
    }
