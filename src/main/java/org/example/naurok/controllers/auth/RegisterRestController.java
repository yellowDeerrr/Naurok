package org.example.naurok.controllers.auth;

import org.example.naurok.payload.requests.auth.RegisterRequest;
import org.example.naurok.repositories.RoleRepository;
import org.example.naurok.repositories.UserRepository;
import org.example.naurok.services.auth.RegisterService;
import org.example.naurok.tables.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class RegisterRestController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return registerService.registerUser(registerRequest) ? ResponseEntity.ok().body("Successfully registered") : ResponseEntity.badRequest().body("Login is already using");
    }
}
