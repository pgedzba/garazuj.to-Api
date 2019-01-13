package to.garazuj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import to.garazuj.exception.CarException;
import to.garazuj.exception.UserException;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;

@Service
public class UsersService {
	
	@Autowired
	UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found " + id));
	}

	public void deleteUser(Long id){
		userRepository.deleteById(id);
	}

	public List<User> searchUsers(String search){
		return userRepository.findWithSearch(search);
	}
}
