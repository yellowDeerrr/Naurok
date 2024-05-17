package org.example.naurok.controllers.test;

import org.example.naurok.repositories.MarksRepository;
import org.example.naurok.security.CustomUserDetails;
import org.example.naurok.services.students.MarksService;
import org.example.naurok.tables.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TestController {
    @Autowired
    private MarksService marksService;

    @GetMapping("/test/get/{uuid}")
    public String getTest(Authentication authentication, @PathVariable String uuid) {
        Marks marks = marksService.findUserAnswers(authentication, uuid);
        if (marks == null)
            return "test/test";
        else
            return "redirect:/test/complete/" + uuid;
    }
}
