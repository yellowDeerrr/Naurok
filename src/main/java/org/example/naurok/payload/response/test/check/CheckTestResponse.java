package org.example.naurok.payload.response.test.check;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckTestResponse {
    private int mark;
    private int countUserCorrectAnswers;
    private int countQuestions;
}
