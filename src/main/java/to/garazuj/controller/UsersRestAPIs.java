package to.garazuj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import to.garazuj.model.User;
import to.garazuj.services.UsersService;

import javax.transaction.Transactional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UsersRestAPIs {

	@Autowired
	UsersService usersService;
	
	@GetMapping(value="/users")
	public List<User> getAllUsers(){
		return usersService.getAllUsers();
	}
	
	@GetMapping(value="/user/{id}")
	public User getUser(@PathVariable Long id){
		return usersService.getUser(id);
	}

	@Transactional
	@DeleteMapping(value="user/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		usersService.deleteUser(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
