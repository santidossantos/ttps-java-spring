package ttps.java.grupo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.ExpenseCategory;
import ttps.java.grupo1.repository.ExpenseCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @Transactional(readOnly = true)
    public Optional<ExpenseCategory> findById(Long id){
        return expenseCategoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<ExpenseCategory> findAll(){
        return expenseCategoryRepository.findAll();
    }
}
