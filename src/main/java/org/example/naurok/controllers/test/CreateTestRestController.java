package org.example.naurok.controllers.test;

import lombok.NonNull;
import org.example.naurok.payload.requests.test.CreateTestAnswersRequest;
import org.example.naurok.payload.requests.test.CreateTestQuestionsRequest;
import org.example.naurok.payload.requests.test.CreateTestRequest;
import org.example.naurok.repositories.TestAnswersRepository;
import org.example.naurok.repositories.TestQuestionsRepository;
import org.example.naurok.repositories.TestRepository;
import org.example.naurok.security.CustomUserDetails;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/test")
public class CreateTestRestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestQuestionsRepository testQuestionsRepository;
    @Autowired
    private TestAnswersRepository testAnswersRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateTestRequest request, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Test test = new Test();
        String uuidOfTest = UUID.randomUUID().toString();

        test.setName(request.getName());
        test.setDescription(request.getDescription());
        test.setAuthor(userDetails.getUUID());
        test.setUUID(uuidOfTest);
        testRepository.save(test);


        for (CreateTestQuestionsRequest question : request.getQuestions()) {
            TestQuestions testQuestion = new TestQuestions();
            String questionUUID = UUID.randomUUID().toString();

            testQuestion.setTitle(question.getQuestion());
            testQuestion.setNumberOfTask(question.getNumberOfQuestion());
            testQuestion.setTestUUID(uuidOfTest);
            testQuestion.setUUID(questionUUID);

            testQuestionsRepository.saveAndFlush(testQuestion);

            for (CreateTestAnswersRequest answer : question.getAnswers()) {
                TestAnswers testAnswer = new TestAnswers();

                testAnswer.setAnswer(answer.getAnswer());
                testAnswer.setCorrect(answer.isCorrect());
                testAnswer.setQuestionUUID(questionUUID);

                testAnswersRepository.saveAndFlush(testAnswer);
            }
        }

        return ResponseEntity.ok().body("Successfully created");
    }
}

