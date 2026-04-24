package com.techub.api.service;

import com.techub.api.domain.User;
import com.techub.api.dto.UserLoginDataDTO;
import com.techub.api.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager
    ) {
        this.userRepository =  userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public User authentication(UserLoginDataDTO input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    input.email(),
                    input.senha()
                )
        );

        return userRepository.findByEmail(input.email())
                .orElseThrow(() -> new RuntimeException("Erro ao tentar fazer login"));
    }
}
