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
import org.springframework.web.ErrorResponse;
import ttps.java.grupo1.DTO.AddMemberDTO;
import ttps.java.grupo1.DTO.GroupDTO;
import ttps.java.grupo1.model.Group;

import java.util.List;

@Tag(name = "Group API", description = "Group related API")
public interface GroupApi {

    @Operation(
            summary = "Fetch all groups",
            description = "Fetches all groups entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groups found"),
            @ApiResponse(responseCode = "204", description = "No content",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "204")))
    })
    ResponseEntity<List<Group>> get();


    @Operation(
            summary = "Fetch a group by id",
            description = "Fetches a group entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group found"),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 404")))
    })
    ResponseEntity<Group> get(@Valid Long id);

    @Operation(
            summary = "Creates a group",
            description = "Creates a group entity and saves it to data source." +
                    "Only one member can be added at creation time, for more members use addMember endpoint." +
                    "If more than 1 user is sent, only the first one will be added to the group.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created"),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 400")))
    })
    ResponseEntity<Group> create(@Valid Group group);

    @Operation(
            summary = "Updates a group",
            description = "Updates a group entity and its data in data source, cant update members," +
                    "for that you need to use addMember endpoint")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group updated"),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 404"))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 400")))
    })
    ResponseEntity<Group> update(@Valid Long id, Group group);

    @Operation(
            summary = "Deletes a group",
            description = "Deletes a group entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group deleted"),
            @ApiResponse(responseCode = "404", description = "Group not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 404")))
    })
    ResponseEntity<Group> delete(@Valid Long id);


    @Operation(
            summary = "Adds a member to a group",
            description = "Adds a member to a group entity and its data in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Member added"),
            @ApiResponse(responseCode = "404", description = "Group or member not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 404"))),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(defaultValue = "Error 400")))
    })
    ResponseEntity<Group> addMember(@Valid AddMemberDTO addMemberDTO);

}
