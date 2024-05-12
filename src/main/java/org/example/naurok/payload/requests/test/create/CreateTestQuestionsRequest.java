package org.example.naurok.payload.requests.test.create;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateTestQuestionsRequest {
    @NonNull
    private String question;
    private int numberOfQuestion;
    @NonNull
    private List<CreateTestAnswersRequest> answers;
}
