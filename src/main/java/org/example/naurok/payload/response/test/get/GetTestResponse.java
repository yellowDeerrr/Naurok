package org.example.naurok.payload.response.test.get;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetTestResponse {
    private String name;
    private String description;
    private List<GetTestQuestionsResponse> questions;
}
