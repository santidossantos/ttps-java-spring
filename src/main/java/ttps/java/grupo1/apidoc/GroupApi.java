package ttps.java.grupo1.apidoc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import ttps.java.grupo1.DTO.AddMemberDTO;
import ttps.java.grupo1.DTO.GroupDTO;
import ttps.java.grupo1.model.Group;

@Tag(name = "Group API", description = "Group related API")
public interface GroupApi {

    @Operation(
            summary = "Fetch all groups",
            description = "Fetches all groups entities and their data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groups found")
    })
    ResponseEntity<Iterable<Group>> getGroups();


    @Operation(
            summary = "Fetch a group by id",
            description = "Fetches a group entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group found"),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    ResponseEntity<Group> getGroup(Long id);

    @Operation(
            summary = "Creates a group",
            description = "Creates a group entity and saves it to data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    ResponseEntity<Group> createGroup(Group group);

    @Operation(
            summary = "Updates a group",
            description = "Updates a group entity and its data in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group updated"),
            @ApiResponse(responseCode = "404", description = "Group not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    ResponseEntity<Group> updateGroup(Long id, Group group);

    @Operation(
            summary = "Deletes a group",
            description = "Deletes a group entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group deleted"),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    ResponseEntity<Group> deleteGroup(Long id);


    @Operation(
            summary = "Adds a member to a group",
            description = "Adds a member to a group entity and its data in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Member added"),
            @ApiResponse(responseCode = "404", description = "Group or member not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    ResponseEntity<Group> addMember(AddMemberDTO addMemberDTO);

}
