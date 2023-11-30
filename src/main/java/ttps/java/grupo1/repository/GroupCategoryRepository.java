package ttps.java.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttps.java.grupo1.model.GroupCategory;

@Repository
public interface GroupCategoryRepository extends JpaRepository<GroupCategory, Long> {

    GroupCategory findFirstByOrderById();
}
