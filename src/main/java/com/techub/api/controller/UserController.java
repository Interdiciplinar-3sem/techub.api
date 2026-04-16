package com.techub.api.controller;

import com.techub.api.domain.User;

import com.techub.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User criar(@RequestBody User user) {
        return userService.criarUsuario(user);
    }

    @GetMapping
    public List<User> listarUser() {

        return userService.listar();
    }

    //localhost:8080/api/usuarios/3
    @GetMapping("/{id}")
    public User buscarUserPorId(@PathVariable Long id) {
        return userService.buscar_por_id(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletarUserPorId(@PathVariable Long id) {
        userService.deletar(id);
    }

    @GetMapping("/pontuacao/{id}")
    public Integer obterPontuacao(@PathVariable Long id){ return userService.obter_pontuacao(id); }
}