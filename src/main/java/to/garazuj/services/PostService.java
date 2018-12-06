package to.garazuj.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import to.garazuj.message.request.AddPostForm;
import to.garazuj.model.Post;
import to.garazuj.repository.PostRepository;
import to.garazuj.security.SecurityUtils;

@Service
public class PostService {

	@Autowired
	PostRepository postRepository;
	
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
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
}
