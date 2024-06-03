package org.example.naurok.services.students;

import org.example.naurok.repositories.MarksRepository;
import org.example.naurok.security.CustomUserDetails;
import org.example.naurok.tables.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class MarksService {
    @Autowired
    private MarksRepository marksRepository;

    public void saveUserAnswers(Authentication authentication, String testUUID, int mark, int countUserCorrectAnswers, int countQuestions){
        Marks marks = new Marks();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        marks.setStudentUUID(userDetails.getUUID());
        marks.setMark(mark);
        marks.setTestUUID(testUUID);
        marks.setCountUserCorrectAnswers(countUserCorrectAnswers);
        marks.setCountQuestions(countQuestions);
        marksRepository.save(marks);
    }

    public Marks findUserAnswers(Authentication authentication, String testUUID){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return (Marks) marksRepository.findByStudentUUID(userDetails.getUUID());
    }
}
