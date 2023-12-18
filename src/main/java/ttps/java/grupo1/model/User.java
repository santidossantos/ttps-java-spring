package ttps.java.grupo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
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
    @Column
    private String password;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Group> groups = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JsonIgnore
    private List<User> friends = new ArrayList<>();

    @Column
    private String avatar;

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