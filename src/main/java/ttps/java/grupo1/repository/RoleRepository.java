package ttps.java.grupo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ttps.java.grupo1.model.UserRole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(String name);

}
