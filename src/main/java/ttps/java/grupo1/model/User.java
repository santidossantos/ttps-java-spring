package ttps.java.grupo1.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Table(name="`user`")
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @NotEmpty(message = "El username no puede estar vacio")
    @Column(name = "username", length = 25, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @NotEmpty(message = "El mail no puede estar vacio")
    @Email(message = "Correo electrónico no válido")
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "users")
    private List<Group> groups = new ArrayList<Group>();

    @ManyToMany
    private List<User> friends = new ArrayList<User>();

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

}