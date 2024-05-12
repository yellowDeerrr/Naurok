package org.example.naurok.controllers.test;

import org.example.naurok.payload.response.test.check.CheckTestResponse;
import org.example.naurok.repositories.TestAnswersRepository;
import org.example.naurok.repositories.TestQuestionsRepository;
import org.example.naurok.repositories.TestRepository;
import org.example.naurok.services.test.CheckTestService;
import org.example.naurok.services.test.TestService;
import org.example.naurok.tables.Test;
import org.example.naurok.tables.TestAnswers;
import org.example.naurok.tables.TestQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/test/check")
public class CheckTestRestController {
    @Autowired
    private TestService testService;
    @Autowired
    private CheckTestService checkTestService;
    @PostMapping("/{uuid}")
    public ResponseEntity<CheckTestResponse> checkTest(@PathVariable("uuid") String uuid, @RequestBody Map<String, String> userAnswers) {
        Test test = testService.isExist(uuid);
        if (test == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(checkTestService.checkTest(uuid, userAnswers));
    }
}





