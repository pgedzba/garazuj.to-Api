package to.garazuj.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.services.UserPrinciple;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/me")
public class LoggedUserRestApis {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping()
    public UserPrinciple getCurrentUser(Principal principal) {

        return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
	
	@PutMapping()
	public User editUser(Principal principal, @RequestBody EditUserForm form) {
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
                .getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();
		if(!form.getFirstName().isEmpty())
			u.setFirstName(form.getFirstName());
		if(!form.getLastName().isEmpty())
			u.setLastName(form.getLastName());
		userRepository.save(u);
		
		return u;
	}

}
