package to.garazuj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import to.garazuj.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
	    Optional<History> findById(String username);
}
