package fi.project.projectidea.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupForm {

    @NotEmpty
    @Size(min = 5, max = 30)
    String username = "";

    @NotEmpty
    @Size(min = 8, max = 30)
    String password = "";

    @NotEmpty
    @Size(min = 8, max = 30)
    String verifyPassword = "";

    //Role is set to "USER" because we want all the new users role to be "USER"
    @NotEmpty
    String role = "USER";


}
