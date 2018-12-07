package to.garazuj.message.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AddCommentForm {

	@NotBlank
	private String content;
}
