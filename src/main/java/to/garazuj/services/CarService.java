package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import to.garazuj.exception.CarException;
import to.garazuj.message.request.AddOrEditCarForm;
import to.garazuj.model.Car;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.security.SecurityUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DBFileStorageService dbFileStorageService;

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

    public void uploadPhotos(Long id, MultipartFile[] files){
        Car car = getCar(id)
                .orElseThrow(() -> new CarException("Car not found " + id));
        Arrays.asList(files)
                .stream()
                .map(file -> dbFileStorageService.storeFile(file,car))
                .collect(Collectors.toList());
    }
}
