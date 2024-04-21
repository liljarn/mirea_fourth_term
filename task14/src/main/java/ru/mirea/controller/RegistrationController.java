package ru.mirea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mirea.dto.UserRegistrationRequest;
import ru.mirea.service.user.DefaultUserService;

@RequestMapping("/register")
@RequiredArgsConstructor
@Controller
@Log4j2
public class RegistrationController {
    private final DefaultUserService defaultUserService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        UserRegistrationRequest userDto = new UserRegistrationRequest();
        model.addAttribute("user", userDto);
        return "registration";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationRequest registrationDto) {
        defaultUserService.saveUser(registrationDto);
        log.info("SAVING USER");
        return "redirect:/register?success";
    }

}

