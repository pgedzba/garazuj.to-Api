package to.garazuj.message.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import to.garazuj.model.User;

@Data
public class AddPostForm {
	
	@NotBlank
	public String title;
	
	public User author;
	
	@NotBlank
	public String content;
	
	@NotBlank
	public String shortDescription;
}
