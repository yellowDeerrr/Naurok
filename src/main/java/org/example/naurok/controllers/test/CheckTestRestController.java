package org.example.naurok.controllers.test;

import org.example.naurok.payload.response.test.check.CheckTestResponse;
import org.example.naurok.services.students.MarksService;
import org.example.naurok.services.test.CheckTestService;
import org.example.naurok.services.test.TestService;
import org.example.naurok.tables.Marks;
import org.example.naurok.tables.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private MarksService marksService;

    @PostMapping("/{uuid}")
    public ResponseEntity<CheckTestResponse> checkTest(Authentication authentication, @PathVariable("uuid") String uuid, @RequestBody Map<String, String> userAnswers) {
        Test test = testService.isExist(uuid);
        if (test == null)
            return ResponseEntity.notFound().build();
        Marks marks = marksService.findUserAnswers(authentication, uuid);
        if (marks != null)
            return ResponseEntity.badRequest().build();

        CheckTestResponse response = checkTestService.checkTest(uuid, userAnswers);
        marksService.saveUserAnswers(authentication, uuid, response.getMark(), response.getCountUserCorrectAnswers(), response.getCountQuestions());
        return ResponseEntity.ok(response);
    }
}





