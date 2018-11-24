package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import to.garazuj.consts.CarType;
import to.garazuj.consts.FuelType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name="cars")
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User owner;

    @NotBlank
    private String mark;
    @NotBlank
    private String model;

    private CarType type;

    private int productionYear;
    @NotBlank
    private String engineSize;

    private Long mileage;

    private int horsePower;

    private FuelType fuelType;

    @OneToMany(fetch = LAZY)
    @JoinTable(name = "car_comments",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private List<Comment> comments = new ArrayList<>();

    public Car(User u,String mark, String model, CarType type, int productionYear, String engineSize, Long mileage, int horsePower, FuelType fuelType){
        this.owner = u;
        this.mark = mark;
        this.model = model;
        this.type = type;
        this.productionYear = productionYear;
        this.engineSize = engineSize;
        this.mileage = mileage;
        this.horsePower = horsePower;
        this.fuelType = fuelType;
    }
}