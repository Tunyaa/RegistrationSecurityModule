package com.module.RegistrationSesurityModule.controller;

import com.module.RegistrationSesurityModule.model.User;
import com.module.RegistrationSesurityModule.repository.UserRepository;
import com.module.RegistrationSesurityModule.service.RegistrationService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author tunyaa
 */
@Controller
@RequiredArgsConstructor
public class MainController {

    private final RegistrationService registrationService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "home";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("registration")
    public String registrationUser(@Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        try {
            registrationService.register(user);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "registration";
        }
    }

    @GetMapping("users")
    public String showUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "login";
    }
}
