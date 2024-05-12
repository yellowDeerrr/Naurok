package org.example.naurok.payload.response.test.get;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTestQuestionsResponse {
    @NonNull
    private String question;
    private int numberOfQuestion;
    @NonNull
    private List<GetTestAnswersResponse> answers;
}
