package ttps.java.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.service.ExpenseService;

@RestController
@Validated
@RequestMapping(value = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;


}
