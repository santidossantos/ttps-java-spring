package ttps.java.grupo1.apidoc;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ttps.java.grupo1.DTO.LoginDTO;
import ttps.java.grupo1.DTO.RegisterDTO;
import ttps.java.grupo1.exception.DuplicateConstraintException;
import ttps.java.grupo1.model.User;

@Tag(name = "Auth API")
public interface AuthApi {

    @Operation(
            summary = "Register a user",
            description = "Creates a user entity and saves it to data source.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "User created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content,
                    description = "Error: Bad Request"
            ),
            @ApiResponse(
                    responseCode = "409",
                    content = @Content,
                    description = "Error: Username or email already in use"
            ),
    })
    ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO registerRequest) throws DuplicateConstraintException;


    @Operation(
            summary = "Login a user",
            description = "Authenticates a user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content,
                    description = "User authenticated"
            ),
            @ApiResponse(
                    responseCode = "401",
                    content = @Content,
                    description = "Error: Unauthorized"
            ),
    })
    ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginRequest);
}
