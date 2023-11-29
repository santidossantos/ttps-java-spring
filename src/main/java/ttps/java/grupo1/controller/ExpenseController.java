package ttps.java.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.service.ExpenseService;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(value = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses = expenseService.findAll();
        return expenses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> get(@PathVariable("id") Long id){
        Optional<Expense> expense = expenseService.findById(id);
        return expense.isPresent()
                ? new ResponseEntity<>(expense.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense){
        return new ResponseEntity<Expense>(this.expenseService.saveExpense(expense), HttpStatus.CREATED);
    }

}
