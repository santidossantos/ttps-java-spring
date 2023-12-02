package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.model.User;

@Data
public class GroupDTO {

    @NotEmpty
    @NotNull
    private String name;

    private Boolean hidden;

    private User creator;

    @NotNull
    private GroupCategory category;

    public GroupDTO(String name, Boolean hidden, User creator, GroupCategory category) {
        this.name = name;
        this.hidden = hidden != null ? hidden : false;
        this.creator = creator;
        this.category = category;
    }
}
