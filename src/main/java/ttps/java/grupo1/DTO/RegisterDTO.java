package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String name;

}
