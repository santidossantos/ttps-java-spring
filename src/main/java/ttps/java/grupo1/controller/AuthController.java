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
import ttps.java.grupo1.DTO.UserDTO;
import ttps.java.grupo1.exception.DuplicateConstraintException;
import ttps.java.grupo1.model.User;
import ttps.java.grupo1.service.UserService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String token = this.userService.authenticate(username, password);

        AuthResponseDTO response = new AuthResponseDTO();

        if(token != null) {
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.setMessage("Nombre de usuario o contraseña incorrecta");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
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
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}