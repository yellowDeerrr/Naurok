package org.example.naurok.services.test;

import org.example.naurok.repositories.TestRepository;
import org.example.naurok.security.CustomUserDetails;
import org.example.naurok.tables.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteTestService {
    @Autowired
    private TestRepository testRepository;

    public String deleteTest(Authentication authentication, String testUUID){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userUUID = userDetails.getUUID();
        Optional<Test> testOptional = testRepository.findByUUID(testUUID);

        if (testOptional.isEmpty() || testUUID.equals(userUUID))
            return "Test not found";

        testRepository.delete(testOptional.get());
        return "Test deleted";
    }
}
