package to.garazuj.message.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import to.garazuj.model.User;

@Data
public class AddPostForm {
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String shortDescription;
}
