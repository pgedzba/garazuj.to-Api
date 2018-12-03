package to.garazuj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import to.garazuj.exception.CarException;
import to.garazuj.message.request.AddCarForm;
import to.garazuj.message.response.ResponseMessage;
import to.garazuj.model.Car;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.security.services.UserPrinciple;

@Service
public class CarService {

	@Autowired
	CarRepository carRepository;
	
	public List<Car> getCars(){
		return ((UserPrinciple) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal()).getUser().getCars();
	}
	
	public Car addCar(AddCarForm addCarForm) {
		Car car = new Car();
		User user = ((UserPrinciple) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal()).getUser();

		car.setUser(user);
		car.setBrand(addCarForm.getBrand());
		car.setModel(addCarForm.getModel());
		car.setType(addCarForm.getType());
		car.setProductionYear(addCarForm.getProductionYear());
		car.setEngineSize(addCarForm.getEngineSize());
		car.setMileage(addCarForm.getMileage());
		car.setHorsePower(addCarForm.getHorsePower());
		car.setFuelType(addCarForm.getFuelType());

		return carRepository.save(car);
	}
	
	
	public void deleteCarAdmin(Long id) {
		carRepository.deleteCarById(id);		
	}
	
	public void deleteCarUser(Long id) {
		User user = ((UserPrinciple) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal()).getUser();
		Car car = carRepository.findById(id)
				.orElseThrow(() -> new CarException("Car not found " + id));
		
		try {
		if(!car.getUser().getId().equals(user.getId()))
			throw new CarException("You don't have permission to delete this car");
		carRepository.delete(car);
		}
		catch(CarException ex) {
			throw new CarException("Could not delete car with id: " + id, ex);
		}
	}
	
	public void editCar(AddCarForm addCarForm) {
		User user = ((UserPrinciple) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal()).getUser();
		Car car = carRepository.findById(addCarForm.getId())
				.orElseThrow(() -> new CarException("Car not found " + addCarForm.getId()));
		try {
			if(!car.getUser().getId().equals(user.getId()))
				throw new CarException("You don't have permission to edit this car");
			
			car.setBrand(addCarForm.getBrand());
			car.setModel(addCarForm.getModel());
			car.setType(addCarForm.getType());
			car.setProductionYear(addCarForm.getProductionYear());
			car.setEngineSize(addCarForm.getEngineSize());
			car.setMileage(addCarForm.getMileage());
			car.setHorsePower(addCarForm.getHorsePower());
			car.setFuelType(addCarForm.getFuelType());
			carRepository.save(car);
		}
		catch(CarException ex) {
			throw new CarException("Could not save car with id: " + car.getId(), ex);
		}
	}
}
