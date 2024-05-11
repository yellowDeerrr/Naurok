package org.example.naurok.controllers.test;

import org.example.naurok.payload.requests.test.CreateTestAnswersRequest;
import org.example.naurok.payload.requests.test.CreateTestQuestionsRequest;
import org.example.naurok.payload.requests.test.CreateTestRequest;
import org.example.naurok.payload.response.test.GetTestAnswersResponse;
import org.example.naurok.payload.response.test.GetTestQuestionsResponse;
import org.example.naurok.payload.response.test.GetTestResponse;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestQuestionsRepository testQuestionsRepository;
    @Autowired
    private TestAnswersRepository testAnswersRepository;
    @GetMapping("/get/{uuid}")
    public ResponseEntity<GetTestResponse> getTest(@PathVariable("uuid") String uuid) {
        Optional<Test> testOptional = testRepository.findByUUID(uuid);
        if (testOptional.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        Test test = testOptional.orElseThrow();
        GetTestResponse getTestResponse = new GetTestResponse();
        List<GetTestQuestionsResponse> getTestQuestionsResponseList = new ArrayList<>();
        List<TestQuestions> questions = testQuestionsRepository.findByTestUUID(test.getUUID());

        getTestResponse.setName(test.getName());
        getTestResponse.setDescription(test.getDescription());

        for (TestQuestions question : questions) {
            GetTestQuestionsResponse getTestQuestionsResponse = new GetTestQuestionsResponse();
            getTestQuestionsResponse.setQuestion(question.getTitle());
            getTestQuestionsResponse.setNumberOfQuestion(question.getNumberOfTask());
            getTestQuestionsResponseList.add(getTestQuestionsResponse);

            List<GetTestAnswersResponse> getTestAnswersResponseList = new ArrayList<>();
            List<TestAnswers> answers = testAnswersRepository.findByQuestionUUID(question.getUUID());
            for (TestAnswers answer : answers) {
                GetTestAnswersResponse getTestAnswersResponse = new GetTestAnswersResponse();
                getTestAnswersResponse.setAnswer(answer.getAnswer());
                getTestAnswersResponseList.add(getTestAnswersResponse);
            }
            getTestQuestionsResponse.setAnswers(getTestAnswersResponseList);
        }
        getTestResponse.setQuestions(getTestQuestionsResponseList);
        return ResponseEntity.ok(getTestResponse);
    }
}

