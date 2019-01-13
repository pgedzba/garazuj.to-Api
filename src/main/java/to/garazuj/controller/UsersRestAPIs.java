package to.garazuj.controller;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import to.garazuj.model.User;
import to.garazuj.services.UsersService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UsersRestAPIs {

	@Autowired
	UsersService usersService;
	
	@GetMapping(value="/users")
	public List<User> getAllUsers(@RequestParam Optional<String> search){
		if(search.isPresent())
			return usersService.searchUsers(search.get());
		else
			return usersService.getAllUsers();
	}
	
	@GetMapping(value="/user/{id}")
	public User getUser(@PathVariable Long id){
		return usersService.getUser(id);
	}
}
