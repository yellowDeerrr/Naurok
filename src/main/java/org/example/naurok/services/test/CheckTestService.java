package org.example.naurok.services.test;

import org.example.naurok.payload.response.test.check.CheckTestResponse;
import org.example.naurok.repositories.MarksRepository;
import org.example.naurok.repositories.TestAnswersRepository;
import org.example.naurok.repositories.TestQuestionsRepository;
import org.example.naurok.tables.Marks;
import org.example.naurok.tables.TestAnswers;
import org.example.naurok.tables.TestQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CheckTestService {
    @Autowired
    private TestQuestionsRepository testQuestionsRepository;
    @Autowired
    private TestAnswersRepository testAnswersRepository;

    public CheckTestResponse checkTest(String uuid, Map<String, String> userAnswers){
        List<TestQuestions> testQuestions = testQuestionsRepository.findByTestUUID(uuid);
        int countQuestions = testQuestions.size();
        int countUserCorrectAnswer = 0;

        for (int i = 0; i < userAnswers.size(); i++) {
            List<TestAnswers> testAnswers = testAnswersRepository.findByQuestionUUID(testQuestions.get(i).getUUID());
            String correctAnswer = null;
            for (TestAnswers testAnswer : testAnswers) {
                if (testAnswer.getIsCorrect()){
                    correctAnswer = testAnswer.getAnswer();
                }
            }
            if (userAnswers.get(String.valueOf(i + 1)).equals(correctAnswer)) {
                countUserCorrectAnswer++;
            }
        }
        int mark = (int) Math.round((countUserCorrectAnswer / (double) countQuestions) * 12);

        CheckTestResponse checkTestResponse = new CheckTestResponse();
        checkTestResponse.setMark(mark);
        checkTestResponse.setCountUserCorrectAnswers(countUserCorrectAnswer);
        checkTestResponse.setCountQuestions(countQuestions);
        return checkTestResponse;
    }
}
