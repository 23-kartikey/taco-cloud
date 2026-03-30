package tacos.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.data.UserRepository;
import tacos.security.RegistrationForm;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepo){
        this.passwordEncoder=passwordEncoder;
        this.userRepo=userRepo;
    }
    
    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
