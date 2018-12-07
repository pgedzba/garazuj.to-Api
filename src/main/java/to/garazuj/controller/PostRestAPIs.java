package to.garazuj.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import to.garazuj.message.request.AddCommentForm;
import to.garazuj.message.request.AddPostForm;
import to.garazuj.model.Comment;
import to.garazuj.model.Post;
import to.garazuj.services.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostRestAPIs {

	@Autowired
	PostService postService;
	
	@GetMapping()
	public List<Post> getPosts(){
		return postService.getPosts();
	}
	
	@GetMapping(value="/{id}")
	public Optional<Post> getPost(@PathVariable Long id){
		return postService.getPost(id);
	}
	
	@PostMapping()
	public ResponseEntity<Post> addPost(@Valid @RequestBody AddPostForm addPostForm){
		Post post = postService.addPost(addPostForm);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@DeleteMapping()
	public ResponseEntity<Post> deletePost(HttpServletRequest request, @RequestParam Long id) {
		if (request.isUserInRole("ROLE_ADMIN"))
			postService.deletePostAdmin(id);
		else
			postService.deletePostUser(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
