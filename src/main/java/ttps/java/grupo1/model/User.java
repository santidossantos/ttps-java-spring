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

    @NotEmpty
    @Column(name = "name", length = 25, nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "username", length = 25, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @NotEmpty
    @Email
    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "users")
    private List<Group> groups = new ArrayList<Group>();

    @ManyToMany
    private List<User> friends = new ArrayList<User>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<UserRole> roles = new ArrayList<>();

    public User(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }


}