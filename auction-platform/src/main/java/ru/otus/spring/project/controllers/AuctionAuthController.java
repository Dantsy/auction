package ru.otus.spring.project.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.spring.project.dto.JwtAuthenticationResponse;
import ru.otus.spring.project.dto.UserDto;
import ru.otus.spring.project.services.AuthService;

@Controller
@Slf4j
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionAuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public String createUser(@ModelAttribute("userDto") @Valid UserDto userDto,
                             BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        JwtAuthenticationResponse jwtToken = authService.signIn(userDto);
        Cookie cookie = new Cookie("Authorization", jwtToken.getToken());
        httpServletResponse.addCookie(cookie);

        return "redirect:/auction";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);
        return "login";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie authorizationCookieRemove = new Cookie("Authorization", "");
        authorizationCookieRemove.setMaxAge(0);
        response.addCookie(authorizationCookieRemove);
        return "redirect:/auction";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute(userDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("userDto") @Valid UserDto userDto,
                           BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        JwtAuthenticationResponse jwtToken = authService.signUp(userDto);
        Cookie cookie = new Cookie("Authorization", jwtToken.getToken());
        httpServletResponse.addCookie(cookie);

        return "redirect:/auction";
    }
}