package to.garazuj.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import to.garazuj.exception.FileStorageException;
import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.services.UserPrinciple;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User getCurrentUser() {
		return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal()).getUser();
	}
	
	public User editUser(EditUserForm form) {
		User user = getCurrentUser();
		
		if(!form.getFirstName().isEmpty())
			user.setFirstName(form.getFirstName());
		if(!form.getLastName().isEmpty())
			user.setLastName(form.getLastName());
		
		return userRepository.save(user);
	}
	
	public User addAvatar(MultipartFile file) {
		User user = getCurrentUser();

		try {
			if(file.getName().contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + file.getName());
			}
			user.setProfileImage(Base64.encode(file.getBytes()));
			return userRepository.save(user);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + file.getName() + ". Please try again!", ex);
		}
	}
	
	public User deleteAvatar() {
		User user = getCurrentUser();
		user.setProfileImage(null);
		return userRepository.save(user);
	}
}
