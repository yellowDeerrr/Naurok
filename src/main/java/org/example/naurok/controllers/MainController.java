package org.example.naurok.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/authenticated")
    public String authenticatedPage() {
        return "main";
    }
}
