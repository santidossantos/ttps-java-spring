package ttps.java.grupo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ttps.java.grupo1.DTO.LoginDTO;
import ttps.java.grupo1.security.JwtService;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginRequest) {
        // Aquí deberías autenticar al usuario y verificar las credenciales
        // Si las credenciales son válidas, genera y devuelve el token
        String username = loginRequest.getUsername();
        String token = jwtService.generateToken(username);
        return token;
    }
}
