package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilterDTO {

    @NotNull
    private String filter;

    @NotNull
    private Long userId;
}
