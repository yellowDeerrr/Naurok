package org.example.naurok.payload.requests.auth;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    @NonNull
    private String login;
    @NonNull
    private String firstName;
    @NonNull
    private String secondName;
    @NonNull
    private String password;
}
