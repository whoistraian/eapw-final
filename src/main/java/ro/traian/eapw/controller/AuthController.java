package ro.traian.eapw.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import ro.traian.eapw.service.auth.AuthServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/signin")
    public void signin() {
        authService.signin(null);
    }

    @PostMapping("/signup")
    public void signup() {
        authService.signup(null);
    }
}
