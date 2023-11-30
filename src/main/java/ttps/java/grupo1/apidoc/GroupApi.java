package ttps.java.grupo1.apidoc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ttps.java.grupo1.model.Group;

@Tag(name = "Group", description = "Group Api")
public interface GroupApi {

    @Operation(
            summary = "Fetch all groups",
            description = "Fetches all groups entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    ResponseEntity<Iterable<Group>> getGroups();


}
