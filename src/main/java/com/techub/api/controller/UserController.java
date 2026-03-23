package com.techub.api.controller;

import com.techub.api.domain.User;
import com.techub.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User criar(@RequestBody User user) {
        return userService.criarUsuario(user);
    }

    @GetMapping
    public List<User> listar() {
        return userService.listar();
    }
}