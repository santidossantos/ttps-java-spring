package ttps.java.grupo1.apidoc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.GroupCategory;

@Tag(name = "GroupCategory API", description = "GroupCategory related API")
public interface GroupCategoryApi {

    @Operation(
            summary = "Creates a groupcategory",
            description = "Creates a group category entity and saves it to data source.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created"),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 400")))
    })
    ResponseEntity<GroupCategory> create(@Valid GroupCategory groupCategory);
}
