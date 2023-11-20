package ttps.java.grupo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
}
