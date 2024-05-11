package org.example.naurok.payload.response.test;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.naurok.payload.requests.test.CreateTestAnswersRequest;

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
