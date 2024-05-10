package org.example.naurok.payload.requests.test;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateTestRequest {
    @NonNull
    private String name;
    @NonNull
    private String description;
    private int numberOfQuestion;
    @NonNull
    private List<CreateTestQuestionsRequest> questions;
}
