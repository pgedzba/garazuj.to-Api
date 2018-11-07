package to.garazuj.message.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EditUserForm {
    @Size(min=3, max = 60)
    private String firstName;

    @Size(min = 6, max = 40)
    private String lastName;
  
}