package to.garazuj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import to.garazuj.enums.FuelType;
import to.garazuj.model.Car;
import to.garazuj.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findById(String username);
    Optional<List<Car>> findByUserId(Long ownerId);
    void deleteCarById(Long id);

    @Query("SELECT c FROM Car c WHERE c.user.id = :userId AND (c.brand LIKE CONCAT('%',:search,'%') OR c.model LIKE CONCAT('%',:search,'%') OR c.productionYear LIKE CONCAT('%',:search,'%'))")
    List<Car> findWithSearch(@Param("userId") Long id, @Param("search") String search);

    List<Car> findByUserIdAndFuelTypeIn(Long id, FuelType fuelType);
}