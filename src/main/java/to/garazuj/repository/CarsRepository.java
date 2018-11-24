package to.garazuj.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import to.garazuj.model.Car;
import to.garazuj.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarsRepository extends JpaRepository<Car, Long> {
    Optional<List<Car>> findByOwner(User user);
}