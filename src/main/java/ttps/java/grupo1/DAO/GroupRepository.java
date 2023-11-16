package ttps.java.grupo1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttps.java.grupo1.model.Expense;
import ttps.java.grupo1.model.Group;
import ttps.java.grupo1.model.User;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {


}
