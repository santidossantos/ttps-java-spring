package ttps.java.grupo1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.ExpenseUsersPays;
import ttps.java.grupo1.repository.ExpenseUsersPaysRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ExpenseUsersPaysService {

    @Autowired
    ExpenseUsersPaysRepository expenseUsersPaysRepository;

    @Autowired
    ExpenseService expenseService;

    @Transactional
    public ExpenseUsersPays save(ExpenseUsersPays eup, Long expenseId){
        Optional<Expense> expense = expenseService.findById(expenseId);
        expense.get().getDebtorsUsers().add(eup);
        return this.expenseUsersPaysRepository.save(eup);
    }


}
