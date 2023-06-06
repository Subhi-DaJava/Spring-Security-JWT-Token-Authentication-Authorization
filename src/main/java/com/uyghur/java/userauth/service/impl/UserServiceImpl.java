package com.uyghur.java.userauth.service.impl;

import com.uyghur.java.userauth.domain.User;
import com.uyghur.java.userauth.repository.UserRepository;
import com.uyghur.java.userauth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userReturned = repository.save(user);
        log.info("New User has been successfully saved in database with username:{%s}".formatted(userReturned.getUsername()));
        return "New user with username:{%s} has been successfully saved in database".formatted(user.getUsername());
    }
}
