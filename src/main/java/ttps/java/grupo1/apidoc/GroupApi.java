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
import org.springframework.web.bind.annotation.RequestHeader;
import ttps.java.grupo1.DTO.AddMemberDTO;
import ttps.java.grupo1.DTO.GroupDTO;
import ttps.java.grupo1.model.Group;

import java.util.List;

@Tag(name = "Group API")
public interface GroupApi {

    @Operation(
            summary = "Fetch all groups",
            description = "Fetches all groups entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Group created"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),
    })
    ResponseEntity<List<Group>> get();


    @Operation(
            summary = "Fetch a group by id",
            description = "Fetches a group entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content,
                    description = "Group found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description = "Error: Group not found"
            ),
            @ApiResponse(
                    responseCode = "403",
                    content = @Content,
                    description = "Error: Forbidden"
            ),
    })
    public ResponseEntity<Group> get(@Valid @PathVariable("id") Long id, @RequestHeader("Authorization") String token);

    @Operation(
            summary = "Creates a group",
            description = "Creates a group entity and saves it to data source." +
                    "Only one member can be added at creation time, for more members use addMember endpoint." +
                    "If more than 1 user is sent, only the first one will be added to the group.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    content = @Content,
                    description = "Group created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description =
                            "Error: Invalid request<br/>" +
                            "Error: The category with id provided doesnt exist<br/>" +
                            "Error: The user creator with id provided doesnt exist<br/>"+
                            "Error: The creator of the group cant be null<br/>"
            ),
    })
    ResponseEntity<Object> create(@Valid GroupDTO groupDTO);

    @Operation(
            summary = "Updates a group",
            description = "Updates a group entity and its data in data source, cant update members," +
                    "for that you need to use addMember endpoint")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Group updated"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "Error: Group not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description =
                            "Error: Invalid request<br/>" +
                            "Error: The group doesnt exist<br/>"+
                            "Error: The category with id provided doesnt exist<br/>" +
                            "Error: The user creator with id provided doesnt exist<br/>"+
                            "Error: The creator of the group cant be changed<br/>"
            ),
    })
    ResponseEntity<Object> update(@Valid @PathVariable Long id, @Valid @RequestBody GroupDTO groupDTO);

    @Operation(
            summary = "Deletes a group",
            description = "Deletes a group entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Group deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "Error: Group not found"
            ),
    })
    ResponseEntity<Group> delete(@Valid Long id);


    @Operation(
            summary = "Adds a member to a group",
            description = "Adds a member to a group entity and its data in data source")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Member added"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "Error: Group not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description =
                            "Error: Invalid request<br/>" +
                            "Error: The user with id provided doesnt exist<br/>"+
                            "Error: The group with id provided doesnt exist<br/>"+
                            "Error: The user is already a member of the group<br/>"
            ),
    })
    ResponseEntity<Object> addMember(@Valid AddMemberDTO addMemberDTO);

    @Operation(
            summary = "Gets my groups",
            description = "Gets groups from logged user with token provided")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Groups found"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),

    })
    public ResponseEntity<List<Group>> findMyGroups(@RequestHeader("Authorization") String token);
}
