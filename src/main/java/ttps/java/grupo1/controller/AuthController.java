package ttps.java.grupo1.controller;

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
import ttps.java.grupo1.security.JwtService;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginRequest) {
        // Aquí deberías autenticar al usuario y verificar las credenciales
        // Si las credenciales son válidas, genera y devuelve el token
        String username = "juan_perez"; // Reemplaza esto por el nombre de usuario del usuario autenticado

        String token = jwtService.generateToken(username);
        System.out.println("Token generado: " + token);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);

    }
}
