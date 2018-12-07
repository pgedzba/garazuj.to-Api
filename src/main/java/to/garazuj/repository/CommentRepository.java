package to.garazuj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import to.garazuj.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	Optional<Comment> findById(Long id);
}
