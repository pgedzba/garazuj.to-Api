package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import to.garazuj.exception.CommentException;
import to.garazuj.exception.PostException;
import to.garazuj.message.request.AddCommentForm;
import to.garazuj.message.request.AddOrEditCarForm;
import to.garazuj.model.Car;
import to.garazuj.model.Comment;
import to.garazuj.model.Post;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.repository.CommentRepository;
import to.garazuj.repository.PostRepository;
import to.garazuj.security.SecurityUtils;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CarRepository carRepository;

    @Transactional
    public void addCommentPost(Long postId, AddCommentForm addCommentForm) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found " + postId));
        Comment comment = new Comment();

        comment.setAuthor(SecurityUtils.getCurrentUser());
        comment.setContent(addCommentForm.getContent());
        comment.setCreateDataTime();

        post.getComments().add(comment);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("Post not found " + postId));
        return post.getComments();
    }

    @Transactional
    public void deleteCommentAdmin(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public void deleteCommentUser(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new PostException("Comment not found " + id));
        try {
            if(!comment.getAuthor().getId().equals(SecurityUtils.getCurrentUser().getId()))
                throw new CommentException("You don't have permissions to delete comment with id:"+id);
            commentRepository.deleteById(id);
        }
        catch(NullPointerException ex) {
            throw new CommentException("Could not delete comment with id:"+id);
        }
    }

    @Transactional
    public void addCommentCar(Long carId, AddCommentForm addCommentForm) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new PostException("Post not found " + carId));
        Comment comment = new Comment();

        comment.setAuthor(SecurityUtils.getCurrentUser());
        comment.setContent(addCommentForm.getContent());
        comment.setCreateDataTime();

        car.getComments().add(comment);
        carRepository.save(car);
    }

    @Transactional(readOnly = true)
    public List<Comment> getCommentsCar(Long carId){
    	Car car = carRepository.findById(carId)
                .orElseThrow(() -> new PostException("Post not found " + carId));
        return car.getComments();
    }
}
