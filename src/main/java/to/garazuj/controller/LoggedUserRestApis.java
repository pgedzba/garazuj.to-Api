package to.garazuj.controller;

import java.io.IOException;
import java.security.Principal;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import to.garazuj.exception.FileStorageException;
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
    public UserPrinciple getCurrentUser() {

        return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

	@PutMapping()
	public ResponseEntity editUser(@RequestBody EditUserForm form) {
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
                .getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();
		if(!form.getFirstName().isEmpty())
			u.setFirstName(form.getFirstName());
		if(!form.getLastName().isEmpty())
			u.setLastName(form.getLastName());
		userRepository.save(u);

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity addAvatar(@RequestParam("file") MultipartFile file) {
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();

		try {
			if(file.getName().contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + file.getName());
			}
			u.setProfileImage(Base64.encode(file.getBytes()));
			userRepository.save(u);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + file.getName() + ". Please try again!", ex);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity deleteAvatar() {
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();
		u.setProfileImage(null);
		userRepository.save(u);
		return new ResponseEntity(HttpStatus.OK);
	}
}
