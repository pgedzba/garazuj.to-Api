package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import to.garazuj.exception.CarException;
import to.garazuj.exception.CommentException;
import to.garazuj.exception.PostException;
import to.garazuj.message.request.AddOrEditCarForm;
import to.garazuj.model.Car;
import to.garazuj.model.Comment;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.security.SecurityUtils;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car addCar(AddOrEditCarForm addOrEditCarForm) {
        Car car = new Car();
        User user = SecurityUtils.getCurrentUser();

        car.setUser(user);
        car.setBrand(addOrEditCarForm.getBrand());
        car.setModel(addOrEditCarForm.getModel());
        car.setType(addOrEditCarForm.getType());
        car.setProductionYear(addOrEditCarForm.getProductionYear());
        car.setEngineSize(addOrEditCarForm.getEngineSize());
        car.setMileage(addOrEditCarForm.getMileage());
        car.setHorsePower(addOrEditCarForm.getHorsePower());
        car.setFuelType(addOrEditCarForm.getFuelType());

        carRepository.save(car);
        return car;
    }

    public List<Car> getCarsForCurrentUser() {
        return SecurityUtils.getCurrentUser().getCars();
    }

    public void deleteCarAdmin(Long id) {
        carRepository.deleteCarById(id);
    }

    public void deleteCarUser(Long id) {
    	 Car car = carRepository.findById(id)
                 .orElseThrow(() -> new CarException("Car not found " + id));
         try {
             if(!car.getUser().getId().equals(SecurityUtils.getCurrentUser().getId()))
                 throw new CarException("You don't have permissions to delete car with id:"+id);
             carRepository.deleteCarById(id);
         }
         catch(NullPointerException ex) {
             throw new CarException("Could not delete car with id:"+id);
         }
    }
    
    public Car editCar(AddOrEditCarForm addOrEditCarForm) {
        if (carRepository.findById(addOrEditCarForm.getId()).isPresent()) {
            Car car = carRepository.findById(addOrEditCarForm.getId()).get(); //TODO: Make it access database only once
            car.setBrand(addOrEditCarForm.getBrand());
            car.setModel(addOrEditCarForm.getModel());
            car.setType(addOrEditCarForm.getType());
            car.setProductionYear(addOrEditCarForm.getProductionYear());
            car.setEngineSize(addOrEditCarForm.getEngineSize());
            car.setMileage(addOrEditCarForm.getMileage());
            car.setHorsePower(addOrEditCarForm.getHorsePower());
            car.setFuelType(addOrEditCarForm.getFuelType());
            carRepository.save(car);

            return car;
        } else
            return null;
    }
    
    public Optional<Car> getCar(Long id) {
    	return carRepository.findById(id);
    }
}
