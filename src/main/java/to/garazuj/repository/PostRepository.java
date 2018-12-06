package to.garazuj.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import to.garazuj.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	Optional<Post> findById(Long id);
	void deleteById(Long id);
	List<Post> findAll();

}
