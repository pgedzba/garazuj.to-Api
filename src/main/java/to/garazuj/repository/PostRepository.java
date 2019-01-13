package to.garazuj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import to.garazuj.model.Post;
import to.garazuj.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findById(Long id);
	void deleteById(Long id);
	List<Post> findAll();

	@Query("SELECT p FROM Post p WHERE p.title LIKE CONCAT('%',:search,'%')")
	List<Post> findWithSearch(@Param("search") String search);

}
