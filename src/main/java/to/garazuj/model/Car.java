package to.garazuj.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private String name; // Manufacturer, model, etc

    @NotBlank
    private int productionYear;

    @NotBlank
    private int engineSize;

    @NotBlank
    private Long mileage;
}