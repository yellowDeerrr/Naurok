package org.example.naurok.payload.response.account.get;

import lombok.Getter;
import lombok.Setter;
import org.example.naurok.tables.Marks;
import org.example.naurok.tables.Test;

import java.util.List;

@Getter
@Setter
public class GetAccountResponse {
    private String firstNameAndLastName;
    private List<Marks> marks;
    private List<Test> tests;
}
