package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
