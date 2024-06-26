package org.example.naurok.payload.requests.test.create;

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
    @NonNull
    private List<CreateTestQuestionsRequest> questions;
}
