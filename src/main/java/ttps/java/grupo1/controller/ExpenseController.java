package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import ttps.java.grupo1.DTO.AddExpenseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.DTO.ExpenseUsersPaysDTO;
import ttps.java.grupo1.model.*;
import ttps.java.grupo1.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseUsersPaysService eupService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @GetMapping("/")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        List<Expense> expenses = expenseService.findAll();
        return expenses.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id){
        Optional<Expense> expense = expenseService.findById(id);
        return expense.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expense.get(), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<Object> createExpense(@Valid @RequestBody AddExpenseDTO expenseDTO){
        Optional<User> user = userService.findById(expenseDTO.getPayingUser().getId());
        Map<String, String> errorResponse = new HashMap<>();
        if(user.isEmpty()){
            errorResponse.put("message", "The paying user written doesnt exists");
        }
        Optional<Group> group = groupService.findById(expenseDTO.getGroup().getId());
        if(group.isEmpty()){
            errorResponse.put("message", "The group written doesnt exists");
        }
        Optional<ExpenseCategory> expenseCategory = expenseCategoryService.findById(expenseDTO.getCategory().getId());
         if(expenseCategory.isEmpty()) {
             errorResponse.put("message", "The category written doesnt exists");
             return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
         }
        Expense expense = new Expense(expenseDTO.getAmount(),expenseDTO.getName(), expenseDTO.getDate(), expenseDTO.getImg(), expenseDTO.getGroup(), expenseDTO.getCategory(), expenseDTO.getPayingUser(), expenseDTO.getExpenseStrategy());
        return new ResponseEntity<>(this.expenseService.saveExpense(expense), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateExpenseById(@PathVariable("id") Long id, @RequestBody Expense dataForUpdate) {
        Optional<Expense> expense = expenseService.findById(id);
        Map<String, String> errorResponse = new HashMap<>();
        if (expense.isEmpty()) {
            errorResponse.put("message", "That expense doesnt exists");
        }
        Optional<User> user = userService.findById(dataForUpdate.getPayingUser().getId());
        if(user.isEmpty()){
            errorResponse.put("message", "The paying user written doesnt exists");
        }
        Optional<Group> group = groupService.findById(dataForUpdate.getGroup().getId());
        if(group.isEmpty()){
            errorResponse.put("message", "The group written doesnt exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        Optional<ExpenseCategory> expenseCategory = expenseCategoryService.findById(dataForUpdate.getCategory().getId());
        return new ResponseEntity<>(expenseService.updateExpense(expense.get(), dataForUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpenseById(@PathVariable("id") Long id){
        return this.expenseService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}/debtorUser")
    public ResponseEntity<Object> addDebtorUser(@PathVariable("id") Long id, @Valid @RequestBody ExpenseUsersPaysDTO eupDTO){
        ExpenseUsersPays eup = new ExpenseUsersPays(eupDTO.getAmountPayed(), eupDTO.getIsPayed(), eupDTO.getUser());
        Optional<Expense> expense = expenseService.findById(id);
        Map<String, String> errorResponse = new HashMap<>();
        if (expense.isEmpty()) {
            errorResponse.put("message", "That expense doesnt exists");
        }
        Optional<User> user = userService.findById(eup.getUser().getId());
        if(user.isEmpty()) {
            errorResponse.put("message", "That user doesnt exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(eupService.save(eup,id), HttpStatus.CREATED);
    }
}
