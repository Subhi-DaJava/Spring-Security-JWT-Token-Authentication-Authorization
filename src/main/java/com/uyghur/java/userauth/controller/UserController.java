package com.uyghur.java.userauth.controller;

import com.uyghur.java.userauth.domain.User;
import com.uyghur.java.userauth.domain.UserDTO;
import com.uyghur.java.userauth.jwt_service.JwtService;
import com.uyghur.java.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping
    public String greetings() {
        return "Welcome to our App";
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveNewUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/auth-token")
    public String authAndGenerateJWT(@RequestBody UserDTO user) {
        log.debug("authAndGenerateJwt method starts here in UserController");
        Authentication authenticationUser =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authenticationUser.isAuthenticated()) {
            log.info("User has been successfully authenticated and get the the valide Token");
            // https://jwt.io/
            return jwtService.generateJwtToken(user.getUsername());
        } else {
            log.debug("Bad credentials!!");
            throw new UsernameNotFoundException("Bad credentials!!");
        }
    }
}
