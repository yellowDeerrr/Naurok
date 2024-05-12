package org.example.naurok.services.test;

import org.example.naurok.payload.response.test.get.GetTestAnswersResponse;
import org.example.naurok.payload.response.test.get.GetTestQuestionsResponse;
import org.example.naurok.payload.response.test.get.GetTestResponse;
import org.example.naurok.repositories.TestAnswersRepository;
import org.example.naurok.repositories.TestQuestionsRepository;
import org.example.naurok.repositories.TestRepository;
import org.example.naurok.tables.Test;
import org.example.naurok.tables.TestAnswers;
import org.example.naurok.tables.TestQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestQuestionsRepository testQuestionsRepository;
    @Autowired
    private TestAnswersRepository testAnswersRepository;

    public Test isExist(String UUID){
        Optional<Test> testOptional = testRepository.findByUUID(UUID);
        return testOptional.orElse(null);
    }
    public GetTestResponse getTestResponse(Test test){
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
        return getTestResponse;
    }
}
