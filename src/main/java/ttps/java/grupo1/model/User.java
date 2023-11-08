package ttps.java.grupo1.model;

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

    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @Column(name = "username", length = 25, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @NotEmpty(message = "El mail no puede estar vacio")
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