package ttps.java.grupo1.apidoc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ttps.java.grupo1.DTO.AddExpenseDTO;
import ttps.java.grupo1.DTO.ExpenseUsersPaysDTO;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.Group;

import java.util.List;

@Tag(name = "Expense API", description = "Expense related API")
public interface ExpenseApi {

    @Operation(
            summary = "Fetch all expenses",
            description = "Fetches all expenses entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Obtain all expenses successfully"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No expenses in the data base"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "You are not authorized to make this request"
            ),
    })
    ResponseEntity<List<Expense>> getAllExpenses();

    @Operation(
            summary = "Fetch a expense with the id",
            description = "Fetches all the data of a expense with the id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Obtain the expenses successfully"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "That expense doesnt exists in the database"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "You are not authorized to make this request"
            ),
    })
    ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long id);

    @Operation(
            summary = "Create a expense",
            description = "Create a expense with the name, amount, date, image, group, category, strategy and the paying user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content,
                    description = "The create of the expense is sucefully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description = "Some necessary parameter is null or send data that doesn't exists"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "You are not authorized to make this request"
            ),
    })
    ResponseEntity<Object> createExpense(@Valid @RequestBody AddExpenseDTO expenseDTO);

    @Operation(
            summary = "Update a expense",
            description = "Update the data of a expense")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "The update of the expense is sucefully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description = "Some necessary parameter is null or send data that doesn't exists"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "You are not authorized to make this request"
            ),
    })
    public ResponseEntity<Object> updateExpenseById(@PathVariable("id") Long id, @RequestBody Expense dataForUpdate);

    @Operation(
            summary = "Delete a expense",
            description = "Delete a expense with the id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "Delete expense sucefully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "Doent exists a expense with that id"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "You are not authorized to make this request"
            ),
    })
    public ResponseEntity<Expense> deleteExpenseById(@PathVariable("id") Long id);

    @Operation(
            summary = "Add a debtor user",
            description = "Add a debtor user to a expense")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content,
                    description = "Add a debtor user sucefully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description = "Some necessary parameter is null or send data that doesn't exists"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "You are not authorized to make this request"
            ),
    })
    public ResponseEntity<Object> addDebtorsUsers(@PathVariable("id") Long id, @Valid @RequestBody List<ExpenseUsersPaysDTO> eupDTOList);

}
