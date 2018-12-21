package to.garazuj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import to.garazuj.exception.CarException;
import to.garazuj.exception.UserException;
import to.garazuj.message.request.AddActionForm;
import to.garazuj.model.Car;
import to.garazuj.model.History;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.repository.HistoryRepository;
import to.garazuj.repository.UserRepository;

@Service
public class HistoryService {

	@Autowired
	HistoryRepository historyRepository;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public ResponseEntity<?> addAction(Long carID, AddActionForm addActionForm){
		Car car = carRepository.findById(carID)
                .orElseThrow(() -> new CarException("Car not found " + carID));
		User user =  userRepository.findById(addActionForm.getUserID())
                .orElseThrow(() -> new UserException("User not found " + addActionForm.getUserID()));
		History history = new History();
		history.setCar(car);
		history.setUser(user);
		history.setDate(addActionForm.getDate());
		history.setDescription(addActionForm.getDescription());
		history.setPrice(addActionForm.getPrice());
		
		historyRepository.save(history);
		
		return new ResponseEntity<>(history,HttpStatus.OK);
	}
	
	public List<History> getCarHisotry(Long carID){
		Car car = carRepository.findById(carID)
                .orElseThrow(() -> new CarException("Car not found " + carID));
		
		return car.getHistory();
	}
	
	public List<History> getUserHistory(Long userID){
		User user =  userRepository.findById(userID)
                .orElseThrow(() -> new UserException("User not found " + userID));
		
		return user.getHistory();
	}
}
