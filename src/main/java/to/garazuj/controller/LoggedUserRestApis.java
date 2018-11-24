package to.garazuj.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import to.garazuj.message.request.CarForm;
import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.Car;
import to.garazuj.model.User;
import to.garazuj.repository.CarsRepository;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.services.UserPrinciple;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api/me")
public class LoggedUserRestApis {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CarsRepository carsRepository;

	@GetMapping()
    public UserPrinciple getCurrentUser() {

        return ((UserPrinciple) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
	
	@PutMapping()
	public User editUser(@RequestBody EditUserForm form) {
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
                .getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();
		if(!form.getFirstName().isEmpty())
			u.setFirstName(form.getFirstName());
		if(!form.getLastName().isEmpty())
			u.setLastName(form.getLastName());
		userRepository.save(u);
		
		return u;
	}

	@PostMapping(value="/car")
	public ResponseEntity addCar(@RequestBody CarForm form){
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();
		Car car = new Car(u,form.getMark(),form.getModel(),form.getType(),
				form.getProductionYear(), form.getEngineSize(),form.getMileage(),form.getHorsePower(),form.getFuelType());
		List<Car> cars = u.getCars();
		cars.add(car);
		u.setCars(cars);
		carsRepository.save(car);

		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(value="/car")
	public List<Car> getCars(){
		UserPrinciple loggedUser = (UserPrinciple)SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
		User u = userRepository.findByUsername(loggedUser.getUsername()).get();

		return u.getCars();
	}
}
