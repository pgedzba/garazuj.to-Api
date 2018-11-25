package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import to.garazuj.message.request.AddCarForm;
import to.garazuj.message.request.SignUpForm;
import to.garazuj.message.response.ResponseMessage;
import to.garazuj.model.Car;
import to.garazuj.model.Role;
import to.garazuj.model.RoleName;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.security.services.UserPrinciple;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.beans.Transient;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/car")
public class CarRestAPIs {

	@Autowired
	CarRepository carRepository;

	@PostMapping()
	public ResponseEntity<?> addCar(@Valid @RequestBody AddCarForm addCarForm) {
		Car car = new Car();
		User user = ((UserPrinciple) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal()).getUser();

		car.setOwner(user);
		car.setBrand(addCarForm.getBrand());
		car.setModel(addCarForm.getModel());
		car.setType(addCarForm.getType());
		car.setProductionYear(addCarForm.getProductionYear());
		car.setEngineSize(addCarForm.getEngineSize());
		car.setMileage(addCarForm.getMileage());
		car.setHorsePower(addCarForm.getHorsePower());
		car.setFuelType(addCarForm.getFuelType());

		carRepository.save(car);

		return new ResponseEntity<>(new ResponseMessage("Car added successfully!"), HttpStatus.OK);
	}

	@GetMapping()
	public List<Car> getCars(){
		List<Car> cars;
		User user = ((UserPrinciple) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal()).getUser();
		if(carRepository.findByOwnerId(user.getId()).isPresent())
			cars =carRepository.findByOwnerId(user.getId()).get();
		else
			cars = Collections.emptyList();

		return cars;
	}

	@Transactional
	@DeleteMapping()
	public ResponseEntity<?> deleteCar(@RequestParam Long index){
		carRepository.deleteCarById(index);

		return new ResponseEntity<>(new ResponseMessage("Car deleted successfully!"), HttpStatus.OK);
	}

}