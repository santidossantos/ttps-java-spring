package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.model.strategy.ExpenseStrategy;
import ttps.java.grupo1.service.ExpenseStrategyService;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/expense-strategy", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseStrategyController {

    @Autowired
    private ExpenseStrategyService expenseStrategyService;

    @GetMapping("/")
    public ResponseEntity<List<ExpenseStrategy>> getAllExpenseStrategies(){
        List<ExpenseStrategy> expenses = expenseStrategyService.findAll();
        return expenses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseStrategy> getExpenseStrategyById(@PathVariable("id") Long id){
        Optional<ExpenseStrategy> expense = expenseStrategyService.findById(id);
        return expense.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expense.get(), HttpStatus.OK);
    }
}
