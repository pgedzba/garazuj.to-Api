package to.garazuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import to.garazuj.model.Report;

@Repository
public interface ReportsRepository extends JpaRepository<Report, Long> {
}
