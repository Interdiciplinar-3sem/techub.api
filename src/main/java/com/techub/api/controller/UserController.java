package com.techub.api.controller;

import com.techub.api.domain.User;
import com.techub.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> criar(){
        // valores fixos apenas para teste
        User user = new User();
        user.setNome("João Teste");
        user.setEmail("joao@teste.com");
        user.setSenha("123456");

        User criado = userService.criar(user);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<User>> listar(){
        List<User> users = userService.listar();
        return ResponseEntity.ok(users);
    }
}


