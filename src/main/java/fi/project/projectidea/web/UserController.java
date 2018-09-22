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

    @Autowired
    UserRepository userRepository;

    //returns the sign up page
    @GetMapping("/signup")
    public String login(Model model) {
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    //Validates our sign up form
    @PostMapping("/saveuser")
    public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (signupForm.getPassword().equals(signupForm.getVerifyPassword())) {
                String pwd = signupForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                User newUser = new User();
                newUser.setUsername(signupForm.getUsername());
                newUser.setPassword(hashPwd);
                newUser.setRole(signupForm.getRole());

                if (userRepository.findByUsername(signupForm.getUsername()) == null) {
                    userRepository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username is taken");
                    return "signup";
                }
            } else {
                bindingResult.rejectValue("verifyPassword", "err.passVerify", "Passwords does not match");
                return "signup";
            }
        } else {
            return "signup";
        }
        return "redirect:login";
    }

}
