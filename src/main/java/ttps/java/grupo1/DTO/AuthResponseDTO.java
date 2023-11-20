package ttps.java.grupo1.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;

    private String tokenType = "Bearer ";  // No quitar el espacio al final

    public AuthResponseDTO(String token) {
        this.token = token;
    }

}
