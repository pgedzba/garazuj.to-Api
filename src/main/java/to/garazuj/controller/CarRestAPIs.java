package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import to.garazuj.message.request.AddCarForm;
import to.garazuj.message.response.ResponseMessage;
import to.garazuj.model.Car;
import to.garazuj.services.CarService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CarRestAPIs {

	@Autowired
	CarService carService;

	@PostMapping(value="/car")
	public ResponseEntity<?> addCar(@Valid @RequestBody AddCarForm addCarForm) {
		
		Car car = carService.addCar(addCarForm);
		
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping(value="/car")
	public List<Car> getCars(){
		
		return carService.getCars();
	}

	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value="/admin/car")
	public ResponseEntity<?> deleteCarAdmin(@RequestParam Long index){
		carService.deleteCarAdmin(index);

		return new ResponseEntity<>(new ResponseMessage("Car deleted successfully!"), HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@DeleteMapping(value="/car")
	public ResponseEntity<?> deleteCarUser(@RequestParam Long index){
		carService.deleteCarUser(index);

		return new ResponseEntity<>(new ResponseMessage("Car deleted successfully!"), HttpStatus.OK);
	}
	
	@PutMapping(value="/car")
	public ResponseEntity<?> editCar(@RequestBody AddCarForm addCarForm){
		carService.editCar(addCarForm);
		return new ResponseEntity<>(new ResponseMessage("Car edited successfully!"), HttpStatus.OK);
	}

}