package org.example.naurok.controllers.test;

import org.example.naurok.payload.requests.test.create.CreateTestAnswersRequest;
import org.example.naurok.payload.requests.test.create.CreateTestQuestionsRequest;
import org.example.naurok.payload.requests.test.create.CreateTestRequest;
import org.example.naurok.repositories.TestAnswersRepository;
import org.example.naurok.repositories.TestQuestionsRepository;
import org.example.naurok.repositories.TestRepository;
import org.example.naurok.security.CustomUserDetails;
import org.example.naurok.services.test.CreateTestService;
import org.example.naurok.services.test.TestService;
import org.example.naurok.tables.Test;
import org.example.naurok.tables.TestAnswers;
import org.example.naurok.tables.TestQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/test")
public class CreateTestRestController {
    @Autowired
    private CreateTestService createTestService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateTestRequest request, Authentication authentication) {
        return ResponseEntity.ok().body(createTestService.createTestService(request, authentication));
    }
}

