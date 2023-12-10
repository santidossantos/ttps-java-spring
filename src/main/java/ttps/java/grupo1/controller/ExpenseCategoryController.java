package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.apidoc.ExpenseCategoryApi;
import ttps.java.grupo1.model.ExpenseCategory;
import ttps.java.grupo1.service.ExpenseCategoryService;

import java.util.List;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/expense-category", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseCategoryController implements ExpenseCategoryApi {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @GetMapping("/")
    public ResponseEntity<List<ExpenseCategory>> getAllCategories(){
        List<ExpenseCategory> expenseCategories = expenseCategoryService.findAll();
        return expenseCategories.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(expenseCategories, HttpStatus.OK);
    }


}
