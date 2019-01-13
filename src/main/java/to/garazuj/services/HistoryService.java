package to.garazuj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import to.garazuj.exception.CarException;
import to.garazuj.exception.HistoryException;
import to.garazuj.exception.UserException;
import to.garazuj.message.request.AddActionForm;
import to.garazuj.model.Car;
import to.garazuj.model.History;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.repository.HistoryRepository;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.SecurityUtils;

@Service
public class HistoryService {

	@Autowired
	HistoryRepository historyRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	UserRepository userRepository;

	@Transactional
	public ResponseEntity<?> addAction(Long carID, AddActionForm addActionForm){
		Car car = carRepository.findById(carID)
                .orElseThrow(() -> new CarException("Car not found " + carID));
		History history = new History();
		history.setCar(car);
		history.setUser(SecurityUtils.getCurrentUser());
		history.setDate(addActionForm.getStartDate());
		history.setDescription(addActionForm.getDescription());
		history.setPrice(addActionForm.getPrice());
		
		historyRepository.save(history);
		
		return new ResponseEntity<>(history,HttpStatus.OK);
	}

	@Transactional(readOnly = true)
	public List<History> getCarHistory(Long carID){
		Car car = carRepository.findById(carID)
                .orElseThrow(() -> new CarException("Car not found " + carID));
		
		return car.getHistory();
	}

	@Transactional(readOnly = true)
	public List<History> getUserHistory(Long userID){
		User user =  userRepository.findById(userID)
                .orElseThrow(() -> new UserException("User not found " + userID));
		
		return user.getHistory();
	}

	@Transactional
	public void deleteActionAdmin(Long id) {
		historyRepository.deleteById(id);
	}

	@Transactional
	public void deleteActionUser(Long id) {
		History history = historyRepository.findById(id)
				.orElseThrow(() -> new HistoryException("History item not found " + id));
		try {
			if(!history.getUser().getId().equals(SecurityUtils.getCurrentUser().getId()))
				throw new HistoryException("You don't have permissions to delete history item with id:"+id);
			historyRepository.deleteById(id);
		}
		catch(NullPointerException ex) {
			throw new HistoryException("Could not delete history item with id:"+id);
		}
	}
}
