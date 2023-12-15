package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;
import ttps.java.grupo1.repository.ExpenseStrategyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseStrategyService {

    @Autowired
    ExpenseStrategyRepository expenseStrategyRepository;

    @Transactional(readOnly = true)
    public Optional<ExpenseStrategy> findById(Long id){
        return expenseStrategyRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ExpenseStrategy> findAll(){
        return expenseStrategyRepository.findAll();
    }
}
