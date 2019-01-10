package to.garazuj.message.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AddMechanicForm {

	@NotBlank
	private String title;

	@NotBlank
	private String shortDescription;
}
