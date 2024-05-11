package org.example.naurok.controllers.auth;

import org.example.naurok.payload.requests.auth.RegisterRequest;
import org.example.naurok.repositories.RoleRepository;
import org.example.naurok.repositories.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        String login = registerRequest.getLogin();
        String firstName = registerRequest.getFirstName();
        String secondName = registerRequest.getSecondName();

        if (userRepository.findByLogin(login) != null){
            return ResponseEntity.badRequest().body("Login is already using");
        }else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            UserEntity userEntity = new UserEntity();

            userEntity.setLogin(login);
            userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            userEntity.setFirstName(firstName);
            userEntity.setSecondName(secondName);
            userEntity.setFirstNameAndLastName(firstName + " " + secondName);
            userEntity.setUUID(UUID.randomUUID().toString());
            userEntity.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));

            userRepository.save(userEntity);
            return ResponseEntity.ok().body("Successful");
        }
    }
}
