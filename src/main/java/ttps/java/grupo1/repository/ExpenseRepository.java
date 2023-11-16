package ttps.java.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ttps.java.grupo1.model.Expense;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> getByName(String name);

    @Modifying
    @Query("update Expense exp set exp.amount = :amount where exp.id = :id")
    void updateAmount(Long Id, Double amount);



}
