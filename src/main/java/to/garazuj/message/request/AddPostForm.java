package to.garazuj.message.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import to.garazuj.model.User;

@Data
public class AddPostForm {
	
	@NotBlank
	private String title;
	
	private User author;
	
	@NotBlank
	private String content;
	
	@NotBlank
	private String shortDescription;
}
