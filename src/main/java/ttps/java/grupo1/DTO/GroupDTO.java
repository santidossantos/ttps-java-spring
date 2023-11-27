package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

public class GroupDTO {
    @NotEmpty
    private String name;

    private Boolean hidden;

    public GroupDTO() {
    }

    public GroupDTO(String name, Boolean hidden) {
        this.name = name;
        this.hidden = hidden != null ? hidden : false;
    }
}
