package com.example.www;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public UserModel getUser(@RequestParam(value = "name", defaultValue = "John Doe") String name) {
        return new UserModel(1L, name, "john.doe@example.com");
    }
}

