package ttps.java.grupo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @Column(length = 25, unique = true, nullable = false)
    private String username;

    @JsonIgnore
    private String password;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "users")
    @JsonManagedReference
    private List<Group> groups = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JsonIgnore
    private List<User> friends = new ArrayList<>();

    @OneToMany(fetch = LAZY, cascade = ALL)
    @JsonIgnore
    private List<User> friendsRequests = new ArrayList<>();


    @ManyToMany(fetch = EAGER, cascade = DETACH)
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

    public void addFriend(User friend) {
    	this.friends.add(friend);
    }

}