package com.techub.api.controller;

import com.techub.api.domain.User;
import com.techub.api.dto.UserLoginDataDTO;
import com.techub.api.dto.UserLoginResponse;
import com.techub.api.service.AuthenticationService;
import com.techub.api.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationService authenticationService,
            JwtService jwtService
    ) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDataDTO dto, HttpServletResponse response) {

        User user = authenticationService.authentication(dto);
        String token = jwtService.generateToken(user.getEmail());

        response.addHeader("Set-Cookie",
                "accessToken=" + token +
                "; HttpOnly" +
                "; Path=/" +
                "; Max-Age=3600" +
                "; SameSite=Lax"
        );

        return ResponseEntity.ok(new UserLoginResponse("Sucesso ao criar o token", token));
    }
}