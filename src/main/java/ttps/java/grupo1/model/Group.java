package ttps.java.grupo1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "`group`")
@NoArgsConstructor
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(nullable = false, columnDefinition="BOOLEAN DEFAULT false")
    private boolean hidden;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private List<User> users = new ArrayList<>();

    @ManyToOne
    private GroupCategory category;

    @JsonIgnore
    @OneToMany(mappedBy="group")
    private List<Expense> expenses;

    public Group(String name, boolean hidden, List<User> users) {
        this.name = name;
        this.hidden = hidden;
        this.users = users;
    }

}
