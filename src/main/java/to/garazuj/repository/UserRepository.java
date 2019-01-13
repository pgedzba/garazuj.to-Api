package to.garazuj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import to.garazuj.model.Car;
import to.garazuj.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.username LIKE CONCAT('%',:search,'%') OR u.firstName LIKE CONCAT('%',:search,'%') OR u.lastName LIKE CONCAT('%',:search,'%')")
    List<User> findWithSearch(@Param("search") String search);
}