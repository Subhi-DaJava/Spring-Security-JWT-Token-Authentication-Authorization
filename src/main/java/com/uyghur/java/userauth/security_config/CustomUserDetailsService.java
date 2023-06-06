package com.uyghur.java.userauth.security_config;

import com.uyghur.java.userauth.domain.User;
import com.uyghur.java.userauth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByUsername = userRepository.findByUsername(username);
        log.info("User has been successfully found by username:{%s} from CustomUserDetailsService".formatted(username));

        // This line uses the map method of the Optional object to transform the found userByUserName(User) into a CustomUserDetails object.
        return userByUsername
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User with username:{%s} not found, in CustomUserDetailsService".formatted(username)));
    }
}
