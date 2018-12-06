package to.garazuj.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import to.garazuj.message.request.AddPostForm;
import to.garazuj.model.Post;
import to.garazuj.services.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PostRestAPIs {

	@Autowired
	PostService postService;
	
	@GetMapping(value="/posts")
	public List<Post> getPosts(){
		return postService.getPosts();
	}
	
	@GetMapping(value="/post")
	public Optional<Post> getPost(@RequestParam Long id){
		return postService.getPost(id);
	}
	
	@PostMapping(value="/post")
	public ResponseEntity<Post> addPost(@Valid @RequestBody AddPostForm addPostForm){
		Post post = postService.addPost(addPostForm);
		
		return new ResponseEntity<>(post,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/post")
	public ResponseEntity<Post> deletePost(@RequestParam Long id){
		postService.deletePost(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
