package org.example.naurok.controllers.test;

import org.example.naurok.payload.response.test.get.GetTestResponse;
import org.example.naurok.services.test.TestService;
import org.example.naurok.tables.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestRestController {
    @Autowired
    private TestService testService;

    @GetMapping("/get/{uuid}")
    public ResponseEntity<GetTestResponse> getTest(@PathVariable("uuid") String uuid) {
        Test test = testService.isExist(uuid);
        if (test == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testService.getTestResponse(test));
    }
}

