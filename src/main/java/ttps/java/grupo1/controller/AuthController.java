package ttps.java.grupo1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterDTO registerDTO) {
        User user = new User(registerDTO.getName(), registerDTO.getUsername(),
                registerDTO.getPassword(), registerDTO.getEmail()
        );

        try {
            user = userService.register(user);
        }
        catch (DuplicateConstraintException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = userService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

}
