package org.example.naurok.controllers.test;

import org.example.naurok.repositories.TestRepository;
import org.example.naurok.services.test.DeleteTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class DeleteTestRestController {
    @Autowired
    private DeleteTestService deleteTestService;

    @DeleteMapping("/delete/{uuid}")
    private ResponseEntity<?> deleteTest(Authentication authentication, @PathVariable("uuid") String uuid) {
        return ResponseEntity.ok().body(deleteTestService.deleteTest(authentication, uuid));
    }
}
