//package org.example.naurok.controllers.test;
//
//import org.example.naurok.payload.requests.test.CreateTestAnswersRequest;
//import org.example.naurok.payload.requests.test.CreateTestQuestionsRequest;
//import org.example.naurok.payload.requests.test.CreateTestRequest;
//import org.example.naurok.repositories.TestAnswersRepository;
//import org.example.naurok.repositories.TestQuestionsRepository;
//import org.example.naurok.repositories.TestRepository;
//import org.example.naurok.security.CustomUserDetails;
//import org.example.naurok.tables.Test;
//import org.example.naurok.tables.TestAnswers;
//import org.example.naurok.tables.TestQuestions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//    @Autowired
//    private TestRepository testRepository;
//    @Autowired
//    private TestQuestionsRepository testQuestionsRepository;
//    @Autowired
//    private TestAnswersRepository testAnswersRepository;
//    @GetMapping("/get/{code}")
//    public ResponseEntity<Test> getTest(@PathVariable("code") String code) {
//
//    }
//}
//
