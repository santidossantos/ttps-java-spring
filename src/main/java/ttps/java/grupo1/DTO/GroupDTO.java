package ttps.java.grupo1.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ttps.java.grupo1.model.GroupCategory;
import ttps.java.grupo1.model.User;

@Data
public class GroupDTO {

    @NotBlank
    private String name;

    private Boolean hidden;

    private User creator;

    private String img;

    @NotNull
    private GroupCategory category;

    public GroupDTO(String name, Boolean hidden, User creator, GroupCategory category, String img) {
        this.name = name;
        this.hidden = hidden != null ? hidden : false;
        this.creator = creator;
        this.category = category;
        this.img = img;
    }
}
