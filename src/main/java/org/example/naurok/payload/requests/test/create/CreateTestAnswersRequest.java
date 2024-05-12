package org.example.naurok.payload.requests.test.create;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateTestAnswersRequest {
    @NonNull
    private String answer;
    private Boolean isCorrect;
}
