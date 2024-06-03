package org.example.naurok.services.auth;

import org.example.naurok.payload.requests.auth.RegisterRequest;
import org.example.naurok.repositories.RoleRepository;
import org.example.naurok.repositories.UserRepository;
import org.example.naurok.tables.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public boolean registerUser(RegisterRequest registerRequest){
        String login = registerRequest.getLogin();
        String firstName = registerRequest.getFirstName();
        String secondName = registerRequest.getSecondName();
        Boolean isPrivate = registerRequest.getIsPrivate().equals("true");

        if (userRepository.findByLogin(login) != null){
            return false;
        }else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            UserEntity userEntity = new UserEntity();

            userEntity.setLogin(login);
            userEntity.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            userEntity.setFirstName(firstName);
            userEntity.setSecondName(secondName);
            userEntity.setFirstNameAndLastName(firstName + " " + secondName);
            userEntity.setUUID(UUID.randomUUID().toString());
            userEntity.setIsPrivate(isPrivate);
            userEntity.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").get()));

            userRepository.save(userEntity);
            return true;
        }
    }
}
