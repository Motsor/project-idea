package fi.project.projectidea.web;

import fi.project.projectidea.domain.SignupForm;
import fi.project.projectidea.domain.User;
import fi.project.projectidea.domain.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    returns the sign up page. Added @ModelAttribute annotation to avoid data binding exception.
    */
    @GetMapping("/signup")
    public String login(@ModelAttribute("signupform") SignupForm signupForm, Model model) {
        model.addAttribute("signupform", signupForm);
        return "signup";
    }

    //Validates our sign up form
    @PostMapping("/saveuser")
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        //Checks if the binding result has any errors.
        if (!bindingResult.hasErrors()) {
            //Checks if the given passwords match.
            if (signupForm.getPassword().equals(signupForm.getVerifyPassword())) {

                //Checks if a user with the same username already exists.
                if (userRepository.findByUsername(signupForm.getUsername()) == null) {
                    String pwd = signupForm.getPassword();

                    //Creates a BCrypt encoder and encodes the new users password
                    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                    String hashPwd = bc.encode(pwd);

                    //Creates a new user and sets its fields with the data from the sign up form.
                    User newUser = new User();
                    newUser.setUsername(signupForm.getUsername());
                    newUser.setPassword(hashPwd);
                    newUser.setRole(signupForm.getRole());

                    //Saves the created user to the repository.
                    userRepository.save(newUser);
                } else {
                    //Will reject the value and give an error if a user with the same username already exist.
                    bindingResult.rejectValue("username", "err.username", "Username is taken");
                    return "signup";
                }
            } else {
                //Will give an error if the given passwords do not match.
                bindingResult.rejectValue("verifyPassword", "err.passVerify", "Passwords do not match");
                return "signup";
            }
        } else {
            return "signup";
        }
        return "redirect:login";
    }

}
