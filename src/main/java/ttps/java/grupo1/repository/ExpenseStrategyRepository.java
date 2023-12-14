package ttps.java.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;

@Repository
public interface ExpenseStrategyRepository extends JpaRepository<ExpenseStrategy, Long> {
}
