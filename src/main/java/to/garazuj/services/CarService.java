package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import to.garazuj.message.request.AddOrEditCarForm;
import to.garazuj.model.Car;
import to.garazuj.model.User;
import to.garazuj.repository.CarRepository;
import to.garazuj.security.SecurityUtils;

import java.util.List;

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

    public void deleteCar(Long id) {
        carRepository.deleteCarById(id);
    }

    public Car editCar(@RequestBody AddOrEditCarForm addOrEditCarForm) {
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
}
