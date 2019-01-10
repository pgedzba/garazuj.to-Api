package to.garazuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import to.garazuj.model.History;
import to.garazuj.model.Mechanic;

import java.util.Optional;

@Repository
public interface MechanicsRepository extends JpaRepository<Mechanic, Long> {
	    Optional<Mechanic> findById(String string);
}
