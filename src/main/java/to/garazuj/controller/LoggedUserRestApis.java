package to.garazuj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.User;
import to.garazuj.security.SecurityUtils;
import to.garazuj.services.LoggedUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/me")
public class LoggedUserRestApis {

	@Autowired
	private LoggedUserService loggedUserService;

	@GetMapping()
	public User getCurrentUser() {
		return SecurityUtils.getCurrentUser();
	}

	@PutMapping()
	public ResponseEntity editUser(@RequestBody EditUserForm form) {
		loggedUserService.editUser(form);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity addAvatar(@RequestParam("file") MultipartFile file) {
		loggedUserService.addAvatar(file);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity deleteAvatar() {
		loggedUserService.deleteAvatar();
		return new ResponseEntity(HttpStatus.OK);
	}

}