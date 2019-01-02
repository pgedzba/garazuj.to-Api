package to.garazuj.message.request;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddActionForm {
	
	@NotNull
	private Float price;
		
	private Long userID;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private Date startDate;
}
