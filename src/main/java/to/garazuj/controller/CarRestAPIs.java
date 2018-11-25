package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import to.garazuj.message.request.AddCarForm;
import to.garazuj.message.request.SignUpForm;
import to.garazuj.message.response.ResponseMessage;
import to.garazuj.model.Car;
import to.garazuj.model.Role;
import to.garazuj.model.RoleName;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/car")
public class CarRestAPIs {

	@Autowired
	CarRepository carRepository;

	@PostMapping("/add")
	public ResponseEntity<?> addCar(@Valid @RequestBody AddCarForm addCarForm) {
		Car car = new Car();

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

}