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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ttps.java.grupo1.DTO.FriendRequestDTO;
import ttps.java.grupo1.DTO.UserDTO;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;

import java.io.IOException;
import java.util.List;

@Tag(name = "User API")
public interface UserApi {

    @Operation(
            summary = "Get all users",
            description = "Returns a list of all users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "List of users"
            ),
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),
    })
    ResponseEntity<List<User>> get();

    @Operation(
            summary = "Get a user",
            description = "Returns a user by id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "User"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    ResponseEntity<User> get(@PathVariable("id") Long id);

    @Operation(
            summary = "Get a user",
            description = "Returns a user by username.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "User"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    ResponseEntity<User> getByUsername(@PathVariable("username") String username);

    @Operation(
            summary = "Update a user",
            description = "Updates a user by id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description = "Bad request"
            ),
            @ApiResponse(
                    responseCode = "409",
                    content = @Content,
                    description = "Conflict: username or email already exists"
            ),
    })
    ResponseEntity<User> update(@PathVariable("id") Long id, @Valid @RequestBody UserDTO userDTO);

    @Operation(
            summary = "Delete a user",
            description = "Deletes a user by id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    ResponseEntity<User> delete(@PathVariable("id") Long id);

    @Operation(
            summary = "Add a friend",
            description = "Adds a friend to a user.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    content = @Content,
                    description = "No content"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    ResponseEntity<User> addFriend(@Valid @RequestBody FriendRequestDTO requestDTO);


    @Operation(
            summary = "Get all friends",
            description = "Returns a list of all friends of a user.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "List of friends"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    ResponseEntity<List<User>> getFriends(@PathVariable("id") Long id);

    @Operation(
            summary = "Get a friend",
            description = "Returns a friend of a user.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "Friend found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    ResponseEntity<User> getFriend(@PathVariable("id") Long id, @PathVariable("friendId") Long friendId);

    @Operation(
            summary = "Get all groups of user",
            description = "Returns a list of all groups of a user with provided id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "List of groups"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content,
                    description = "User not found"
            ),
    })
    public ResponseEntity<List<Group>> getGroups(@PathVariable("id") Long id);

    @Operation(
            summary = "Post avatar image to a user",
            description = "Post an image to a user to use as avatar")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "OK"
            ),
    })
    public void createAd(@RequestParam("username") String username, @RequestParam("avatarFile") MultipartFile[] avatarFile) throws IOException;

    @Operation(
            summary = "Get avatar image of a user",
            description = "Get the avatar image of a user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    content = @Content,
                    description = "Internal server error"
            ),
    })
    public ResponseEntity<byte[]> getImages(@PathVariable("username") String username);
}
