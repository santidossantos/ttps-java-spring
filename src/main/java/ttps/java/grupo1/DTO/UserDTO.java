package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

}
