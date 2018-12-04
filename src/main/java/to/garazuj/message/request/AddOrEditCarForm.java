package to.garazuj.message.request;

import lombok.Data;
import to.garazuj.enums.CarType;
import to.garazuj.enums.FuelType;
import javax.validation.constraints.NotBlank;
@Data
public class AddOrEditCarForm {

	private Long id;
	
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
} 