package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FriendRequestDTO {

    @NotNull
    private Long id;

    @NotNull
    private Long friendId;

}
