package to.garazuj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
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

	@Transactional(readOnly = true)
	public List<Post> getPosts(){
		return postRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Post> getPost(Long id){
		return postRepository.findById(id);
	}

	@Transactional
	public Post addPost(AddPostForm addPostForm) {
		Post post = new Post();
		post.setAuthor(SecurityUtils.getCurrentUser());
		post.setContent(addPostForm.getContent());
		post.setTitle(addPostForm.getTitle());
		post.setShortDescription(addPostForm.getShortDescription());
		
		postRepository.save(post);
		
		return post;
	}

	@Transactional
	public void deletePostAdmin(Long id) {
		postRepository.deleteById(id);
	}

	@Transactional
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

	public List<Post> searchPosts(String search){
		return postRepository.findWithSearch(search);
	}
	

}
