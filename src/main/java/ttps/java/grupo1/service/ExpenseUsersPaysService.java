package ttps.java.grupo1.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.ExpenseUsersPays;
import ttps.java.grupo1.repository.ExpenseUsersPaysRepository;

import java.util.List;
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

    @Transactional
    public List<ExpenseUsersPays> saveDebtors(List<ExpenseUsersPays> eupList, Long expenseId){
        Optional<Expense> expense = expenseService.findById(expenseId);
        eupList.forEach(eup -> {
            expense.get().getDebtorsUsers().add(eup);
            this.expenseUsersPaysRepository.save(eup);
        });
        return eupList;
    }

    @Transactional
    public ExpenseUsersPays updateIsPayed(ExpenseUsersPays eupToUpdate){
        eupToUpdate.setIsPayed(!eupToUpdate.getIsPayed());
        return eupToUpdate;
    }

    @Transactional
    public Optional<ExpenseUsersPays> findById(Long id){
        return this.expenseUsersPaysRepository.findById(id);
    }



}
