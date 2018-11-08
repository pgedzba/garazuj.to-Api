package to.garazuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import to.garazuj.model.DBFile;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

}