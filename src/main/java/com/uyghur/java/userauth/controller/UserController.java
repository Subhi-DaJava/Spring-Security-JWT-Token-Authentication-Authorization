package com.uyghur.java.userauth.controller;

import com.uyghur.java.userauth.domain.User;
import com.uyghur.java.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String greetings() {
        return "Welcome to our App";
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveNewUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

}
