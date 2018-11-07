package to.garazuj.controller;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.services.UserPrinciple;

import java.security.Principal;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}

	@GetMapping("/api/test/currentuser")
    public UserPrinciple getCurrentUser(Principal principal) {

        return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
	
	@PutMapping("/api/test/currentuser")
	public @ResponseBody ResponseEntity editUser(@RequestParam Long id, @RequestBody EditUserForm form) {
		User u = null;
		u = userRepository.findById(id).get();
		if(!form.getFirstName().isEmpty())
			u.setFirstName(form.getFirstName());
		if(!form.getLastName().isEmpty())
			u.setLastName(form.getLastName());
		userRepository.save(u);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}