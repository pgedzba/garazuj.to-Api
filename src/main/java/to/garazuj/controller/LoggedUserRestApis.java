package to.garazuj.controller;


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

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/me")
public class LoggedUserRestApis {

	@Autowired
	private UserRepository userRepository;

	@GetMapping()
    public User getCurrentUser() {

        return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUser();
    }
	
	@PutMapping()
	public ResponseEntity editUser(@RequestBody EditUserForm form) {
		User user = ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUser();
		if(!form.getFirstName().isEmpty())
			user.setFirstName(form.getFirstName());
		if(!form.getLastName().isEmpty())
			user.setLastName(form.getLastName());
		userRepository.save(user);

		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity addAvatar(@RequestParam("file") MultipartFile file) {
        User user = ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUser();

		try {
			if(file.getName().contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + file.getName());
			}
			user.setProfileImage(Base64.encode(file.getBytes()));
			userRepository.save(user);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + file.getName() + ". Please try again!", ex);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping()
	public ResponseEntity deleteAvatar() {
        User user = ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUser();
		user.setProfileImage(null);
		userRepository.save(user);
		return new ResponseEntity(HttpStatus.OK);
	}

}
