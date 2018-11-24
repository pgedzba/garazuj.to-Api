package to.garazuj.message.request;

import lombok.Data;
import to.garazuj.consts.CarType;
import to.garazuj.consts.FuelType;

import javax.validation.constraints.NotBlank;

@Data
public class CarForm {
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
}