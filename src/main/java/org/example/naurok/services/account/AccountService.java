package org.example.naurok.services.account;

import org.example.naurok.payload.response.account.get.GetAccountResponse;
import org.example.naurok.repositories.MarksRepository;
import org.example.naurok.repositories.TestRepository;
import org.example.naurok.repositories.UserRepository;
import org.example.naurok.tables.Marks;
import org.example.naurok.tables.Test;
import org.example.naurok.tables.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private String UUID;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private MarksRepository marksRepository;

    public GetAccountResponse getOwnAccount(String UUID){
        this.UUID = UUID;
        GetAccountResponse response = new GetAccountResponse();

        response.setFirstNameAndLastName(getUserFirstNameAndLastName());
        response.setMarks(getUserMarks());
        response.setTests(getUserTests());

        return response;
    }
    public GetAccountResponse getAccount(String UUID) {
        this.UUID = UUID;
        GetAccountResponse response = new GetAccountResponse();
        UserEntity userEntity = userRepository.findByUUID(UUID);

        if (userEntity == null) {
            return null;
        }
        if (userEntity.getIsPrivate()){
            response.setFirstNameAndLastName(userEntity.getFirstNameAndLastName());
            response.setTests(getUserTests());
        }else{
            response.setFirstNameAndLastName(userEntity.getFirstNameAndLastName());
            response.setMarks(getUserMarks());
            response.setTests(getUserTests());
        }
        return response;
    }
    public String getUserFirstNameAndLastName(){
        return userRepository.findByUUID(UUID).getFirstNameAndLastName();
    }
    public List<Marks> getUserMarks(){
        return marksRepository.findMarksByStudentUUID(UUID);
    }
    public List<Test> getUserTests(){
        return testRepository.findByAuthor(UUID);
    }
}
