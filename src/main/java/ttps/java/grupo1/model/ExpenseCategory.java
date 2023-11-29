package ttps.java.grupo1.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "`category_expense`")
@NoArgsConstructor
@Data
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String icon;

    public ExpenseCategory(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }
}
