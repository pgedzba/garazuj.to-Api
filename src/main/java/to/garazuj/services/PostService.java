package to.garazuj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import to.garazuj.exception.PostException;
import to.garazuj.message.request.AddCommentForm;
import to.garazuj.message.request.AddPostForm;
import to.garazuj.model.Comment;
import to.garazuj.model.Post;
import to.garazuj.repository.CommentRepository;
import to.garazuj.repository.PostRepository;
import to.garazuj.security.SecurityUtils;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	public List<Post> getPosts(){
		return postRepository.findAll();
	}
	
	public Optional<Post> getPost(Long id){
		return postRepository.findById(id);
	}
	
	public Post addPost(AddPostForm addPostForm) {
		Post post = new Post();
		post.setAuthor(SecurityUtils.getCurrentUser());
		post.setContent(addPostForm.getContent());
		post.setTitle(addPostForm.getTitle());
		post.setShortDescription(addPostForm.getShortDescription());
		
		postRepository.save(post);
		
		return post;
	}
	
	public void deletePostAdmin(Long id) {
		postRepository.deleteById(id);
	}
	
	public void deletePostUser(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new PostException("Post not found " + id));
		
		try {
			if(!post.getAuthor().getId().equals(SecurityUtils.getCurrentUser().getId()))
				throw new PostException("You don't have permission to delete this post");
			postRepository.delete(post);
			}
			catch(PostException ex) {
				throw new PostException("Could not delete post with id: " + id, ex);
			}
	}
	
	public void addComment(Long postId, AddCommentForm addCommentForm) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostException("Post not found " + postId));
		Comment comment = new Comment();
		
		comment.setAuthor(SecurityUtils.getCurrentUser());
		comment.setContent(addCommentForm.getContent());
		comment.setCreateDataTime();
		
		post.setComments(comment);
		postRepository.save(post);
	}
	
	public List<Comment> getComments(Long postId){
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostException("Post not found " + postId));
		return post.getComments();
	}
	
	public void deleteCommentAdmin(Long id) {
		commentRepository.deleteById(id);
	}
	
	public void deleteCommentUser(Long postId, Long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostException("Post not found " + postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new PostException("Comment not found " + commentId));
		try {
		if(!comment.getAuthor().getId().equals(SecurityUtils.getCurrentUser().getId()))
			throw new PostException("You don't have permissions to delete comment with id:"+postId);
			post.deleteComment(comment);
		}
		catch(NullPointerException ex) {
			throw new PostException("Could not delete comment with id:"+postId);
		}
	}
}
