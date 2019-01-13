package to.garazuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import to.garazuj.model.History;
import to.garazuj.model.Mechanic;

import java.util.List;
import java.util.Optional;

@Repository
public interface MechanicsRepository extends JpaRepository<Mechanic, Long> {
	    Optional<Mechanic> findById(String string);

	@Query("SELECT m FROM Mechanic m WHERE m.title LIKE CONCAT('%',:search,'%')")
	List<Mechanic> findWithSearch(@Param("search") String search);
}
