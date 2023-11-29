package ttps.java.grupo1.service;

import exception.DataNotFoundException;
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
    public void updateExpense(Long id, Expense expenseToUpdate) throws DataNotFoundException {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        Expense expense = optionalExpense.orElseThrow(() -> new DataNotFoundException("Expense not found"));
        expense.setAmount(expenseToUpdate.getAmount());
        expense.setName(expenseToUpdate.getName());
        expense.setDate(expenseToUpdate.getDate());
        expense.setImg(expenseToUpdate.getImg());
        expense.setCategory(expenseToUpdate.getCategory());
        expense.setExpenseStrategy(expenseToUpdate.getExpenseStrategy());
        expense.setDebtorsUsers(expenseToUpdate.getDebtorsUsers());
        expense.setPayingUser(expenseToUpdate.getPayingUser());
    }




}
