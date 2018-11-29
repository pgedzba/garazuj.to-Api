package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import to.garazuj.enums.CarType;
import to.garazuj.enums.FuelType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name="cars")
@Data
@NoArgsConstructor
public class Car {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@JsonIgnore
    @ManyToOne(fetch = EAGER)
    @JoinTable(name = "user_cars",
    joinColumns = @JoinColumn(name = "car_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    private CarType type;

    private int productionYear;

    @NotBlank
    private String engineSize;

    private Long mileage;

    private int horsePower;

    private FuelType fuelType;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "car_comments",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments = new ArrayList<>();
}