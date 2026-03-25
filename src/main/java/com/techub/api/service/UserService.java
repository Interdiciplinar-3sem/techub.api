package com.techub.api.service;

import com.techub.api.domain.User;
import com.techub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User criarUsuario(User user) {

        // valida email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }

        // hash da senha
        String senhaHash = passwordEncoder.encode(user.getSenha());
        user.setSenha(senhaHash);

        // salva
        return userRepository.save(user);
    }

    public List<User> listar() {
        return userRepository.findAll();
    }
}