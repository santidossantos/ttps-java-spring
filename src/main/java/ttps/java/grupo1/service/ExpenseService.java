package ttps.java.grupo1.service;

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
    public List<Expense> findByName(String name){
        return expenseRepository.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<Expense> findAll(){
        return expenseRepository.findAll();
    }

    @Transactional
    public Expense saveExpense(Expense expense){
        return expenseRepository.save(expense);
    }

//    @Transactional
//    public void updateAmount(Long id, Double amount){
//        expenseRepository.updateAmount(id, amount);
//    }

    @Transactional
    public void deleteAll(){
        expenseRepository.deleteAll();
    }

//    public void updateName(Long id, String name){
//        expenseRepository.updateName(id, name);
//    }



}
