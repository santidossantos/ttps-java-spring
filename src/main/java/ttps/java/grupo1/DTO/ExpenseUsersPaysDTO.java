package ttps.java.grupo1.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ttps.java.grupo1.model.User;

@Data
public class ExpenseUsersPaysDTO {

    @NotNull
    private Double amountPayed;

    @NotNull
    private Boolean isPayed;

    @NotNull
    private User user;

}
