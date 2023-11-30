package ttps.java.grupo1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="`category_group`")
@NoArgsConstructor
@Data
public class GroupCategory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length=50, nullable = false)
    private String name;

    private String icon;

    public GroupCategory(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

}
