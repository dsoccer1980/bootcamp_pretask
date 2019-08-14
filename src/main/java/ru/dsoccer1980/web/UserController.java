package ru.dsoccer1980.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/role")
    public String role() {
        return "hello";
    }
}
