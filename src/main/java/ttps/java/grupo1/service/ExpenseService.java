package ttps.java.grupo1.service;

import ttps.java.grupo1.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ttps.java.grupo1.repository.ExpenseRepository;
import ttps.java.grupo1.model.Expense;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;

    @Transactional(readOnly = true)
    public Optional<Expense> findById(Long id){
        return expenseRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Expense> findAll(){
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateExpense(Expense expenseToUpdate, Expense newData){
        expenseToUpdate.setAmount(newData.getAmount());
        expenseToUpdate.setName(newData.getName());
        expenseToUpdate.setDate(newData.getDate());
        expenseToUpdate.setImg(newData.getImg());
        expenseToUpdate.setCategory(newData.getCategory());
        expenseToUpdate.setExpenseStrategy(newData.getExpenseStrategy());
        expenseToUpdate.setDebtorsUsers(newData.getDebtorsUsers());
        expenseToUpdate.setPayingUser(newData.getPayingUser());
        return expenseToUpdate;
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (this.expenseRepository.existsById(id)) {
            this.expenseRepository.deleteById(id);
            return true;
        }
        return false;
    }



}
