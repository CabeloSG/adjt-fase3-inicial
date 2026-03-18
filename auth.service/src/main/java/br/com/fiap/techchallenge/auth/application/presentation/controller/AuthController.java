package br.com.fiap.techchallenge.auth.application.presentation.controller;

import br.com.fiap.techchallenge.auth.infra.service.AuthService;
import br.com.fiap.techchallenge.auth.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    // construtor manual
    public AuthController(AuthService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar novo usuário")
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        service.register(user);
    }

    @Operation(summary = "Login do usuário")
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {

        String token = service.login(
                body.get("email"),
                body.get("password")
        );

        return Map.of("token", token);
    }
}
