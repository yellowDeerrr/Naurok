package org.example.naurok.controllers.test;

import org.example.naurok.payload.response.test.get.GetTestResponse;
import org.example.naurok.payload.response.test.mark.MarksResponse;
import org.example.naurok.repositories.MarksRepository;
import org.example.naurok.services.students.MarksService;
import org.example.naurok.services.test.TestService;
import org.example.naurok.tables.Marks;
import org.example.naurok.tables.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestRestController {
    @Autowired
    private TestService testService;
    @Autowired
    private MarksService marksService;

    @GetMapping("/get/{uuid}")
    public ResponseEntity<GetTestResponse> getTest(@PathVariable("uuid") String uuid) {
        Test test = testService.isExist(uuid);
        if (test == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testService.getTestResponse(test));
    }

    @GetMapping("/get/mark/{uuid}")
    public ResponseEntity<MarksResponse> getMark(Authentication authentication, @PathVariable String uuid){
        Marks mark = marksService.findUserAnswers(authentication, uuid);
        if (mark == null)
            return ResponseEntity.notFound().build();

        MarksResponse markResponse = new MarksResponse();

        markResponse.setMark(mark.getMark());
        markResponse.setCountUserCorrectAnswers(mark.getCountUserCorrectAnswers());
        markResponse.setCountQuestions(mark.getCountQuestions());

        return ResponseEntity.ok().body(markResponse);
    }
}

