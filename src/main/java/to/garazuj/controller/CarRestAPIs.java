package to.garazuj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import to.garazuj.enums.FuelType;
import to.garazuj.message.request.AddOrEditCarForm;
import to.garazuj.message.response.ResponseMessage;
import to.garazuj.model.Car;
import to.garazuj.services.CarService;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/car")
public class CarRestAPIs {

	@Autowired
	private CarService carService;

	@PostMapping()
	public ResponseEntity<Car> addCar(@Valid @RequestBody AddOrEditCarForm addOrEditCarForm) {
		Car car = carService.addCar(addOrEditCarForm);
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Car>> getCars(@RequestParam Optional<String> search, @RequestParam Optional<FuelType> fuelType){
		if(search.isPresent())
			return new ResponseEntity<>(carService.searchCars(search.get()), HttpStatus.OK);
		else if(fuelType.isPresent())
			return new ResponseEntity<>(carService.filterCars(fuelType.get()), HttpStatus.OK);
		else
			return new ResponseEntity<>(carService.getCarsForCurrentUser(), HttpStatus.OK);

	}

	@GetMapping(value="/{carId}")
	public ResponseEntity<Car> getCar(@PathVariable Long carId){
		Optional<Car> car = carService.getCar(carId);
		if(car.isPresent())
			return new ResponseEntity<>(car.get(), HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping()
	public ResponseEntity<?> deleteCar(HttpServletRequest request, @RequestParam Long id){
		if(request.isUserInRole("ROLE_ADMIN"))
			carService.deleteCarAdmin(id);
		else
			carService.deleteCarUser(id);
		
		return new ResponseEntity<>(new ResponseMessage(""), HttpStatus.OK);
	}

	@PutMapping()
	public ResponseEntity<?> editCar(@RequestBody AddOrEditCarForm addOrEditCarForm){
		Car car = carService.editCar(addOrEditCarForm);
		if ( car != null )
			return new ResponseEntity<>(car, HttpStatus.OK);
		else return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PatchMapping(value="/{carId}")
	public ResponseEntity<Car> uploadPhotos(@PathVariable Long carId, @RequestParam("photos") MultipartFile[] files){
		carService.uploadPhotos(carId, files);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}