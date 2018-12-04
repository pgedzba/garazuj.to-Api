package to.garazuj.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;
import to.garazuj.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/me")
public class LoggedUserRestApis {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
	
	@GetMapping()
    public User getCurrentUser() {

        return  userService.getUser();
    }
	
	@PutMapping()
	public ResponseEntity editUser(@RequestBody EditUserForm form) {
		User user = userService.editUser(form);

		return new ResponseEntity(user,HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity addAvatar(@RequestParam("file") MultipartFile file) {
        User user = userService.addAvatar(file);
        
		return new ResponseEntity(user,HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity deleteAvatar() {
        User user = userService.deleteAvatar();
        
		return new ResponseEntity(HttpStatus.OK);
	}

}
