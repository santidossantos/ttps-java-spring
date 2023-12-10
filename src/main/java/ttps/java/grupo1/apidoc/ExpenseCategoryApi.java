package ttps.java.grupo1.apidoc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import ttps.java.grupo1.model.ExpenseCategory;
import java.util.List;

public interface ExpenseCategoryApi {

    @Operation(
            summary = "Fetch all categories",
            description = "Fetches all categories that can belong to an expense")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Obtain all the categories successfully"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),
    })
    public ResponseEntity<List<ExpenseCategory>> getAllCategories();
}
