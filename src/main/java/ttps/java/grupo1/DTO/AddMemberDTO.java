package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddMemberDTO {

    @NotNull
    private Long groupId;

    @NotNull
    private Long userId;

}
