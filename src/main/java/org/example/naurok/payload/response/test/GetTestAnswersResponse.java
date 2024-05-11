package org.example.naurok.payload.response.test;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class GetTestAnswersResponse {
    @NonNull
    private String answer;
}
