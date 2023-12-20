package ttps.java.grupo1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

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
    @Schema(hidden = true)
    private Long id;

    @Column(length = 50)
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean hidden;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    private GroupCategory category;

    @Column
    private String img;

    @OneToMany(mappedBy = "group")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Schema(hidden = true)
    private List<Expense> expenses;

    public Group(String name, boolean hidden, List<User> users, GroupCategory category, String img) {
        this.name = name;
        this.hidden = hidden;
        this.users = users;
        this.category = category;
        this.img = img;
    }

    public void addMember(User user) {
        this.users.add(user);
    }

}
