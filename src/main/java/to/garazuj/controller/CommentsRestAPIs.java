package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import to.garazuj.message.request.AddCommentForm;
import to.garazuj.message.request.AddPostForm;
import to.garazuj.model.Comment;
import to.garazuj.model.Post;
import to.garazuj.services.CommentService;
import to.garazuj.services.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
public class CommentsRestAPIs {

	@Autowired
	CommentService commentService;

	@PutMapping(value="/post/{postId}")
	public ResponseEntity<Comment> addCommentPost(@PathVariable Long postId, @RequestBody AddCommentForm addCommentForm){
		commentService.addCommentPost(postId, addCommentForm);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(value="/post/{postId}")
	public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId){
		return new ResponseEntity<>(commentService.getCommentsPost(postId),HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value="/delete/{commentId}")
	public ResponseEntity<?> deleteComment(HttpServletRequest request, @PathVariable Long commentId){
		if(request.isUserInRole("ROLE_ADMIN"))
			commentService.deleteCommentAdmin(commentId);
		else
			commentService.deleteCommentUser(commentId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
