package to.garazuj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import to.garazuj.exception.CarException;
import to.garazuj.exception.UserException;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;

@Service
public class UsersService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found " + id));
	}

	public void deleteUser(Long id){
		userRepository.deleteById(id);
	}

}
