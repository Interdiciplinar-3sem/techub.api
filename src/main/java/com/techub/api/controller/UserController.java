package com.techub.api.controller;

import com.techub.api.domain.User;
import com.techub.api.repository.UserRepository;
import com.techub.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User criar(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> listarUser() {

        return userRepository.findAll();
    }

    //localhost:8080/api/usuarios/3
    @GetMapping("/{id}")
    public User buscarUserPorId(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarUserPorId(@PathVariable Long id) {

        userRepository.deleteById(id);
    }
}