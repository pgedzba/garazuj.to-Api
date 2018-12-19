package to.garazuj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import to.garazuj.model.User;
import to.garazuj.services.UsersService;

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
}
