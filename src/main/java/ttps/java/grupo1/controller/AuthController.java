package ttps.java.grupo1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.DTO.AuthResponseDTO;
import ttps.java.grupo1.DTO.LoginDTO;
import ttps.java.grupo1.DTO.RegisterDTO;
import ttps.java.grupo1.exception.DuplicateConstraintException;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String token = this.userService.authenticate(username, password);

        if(token != null) {
            return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", "Nombre de usuario o contraseña incorrectos");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDTO registerRequest) throws DuplicateConstraintException {

        User user = new User(
                registerRequest.getName(),
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail()
       );

        try {
            return new ResponseEntity<>(userService.register(user), HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}