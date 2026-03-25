package com.techub.api.domain;

import com.techub.api.domain.User;
import com.techub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
//verifica se credenciais existem no banco de dados
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User usuario = userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(user.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return "Login realizado com sucesso";
    }
}