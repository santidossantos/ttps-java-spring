package ttps.java.grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.ExpenseUsersPays;
import ttps.java.grupo1.service.ExpenseUsersPaysService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(value = "/eup", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseUserPaysController {

    @Autowired
    private ExpenseUsersPaysService eupService;

    @PutMapping("/{id}/isPayed")
    public ResponseEntity<Object> updateIsPayed(@PathVariable("id") Long id){
        Optional<ExpenseUsersPays> eup = eupService.findById(id);
        Map<String, String> errorResponse = new HashMap<>();
        if (eup.isEmpty()) {
            errorResponse.put("message", "That expense users pays doesnt exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        eupService.updateIsPayed(eup.get());
        return new ResponseEntity<>(eup, HttpStatus.OK);
    }
}
