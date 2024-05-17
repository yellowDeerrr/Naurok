package org.example.naurok.payload.response.test.mark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarksResponse {
    private int mark;
    private int countUserCorrectAnswers;
    private int countQuestions;
}
