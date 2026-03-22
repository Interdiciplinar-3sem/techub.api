package com.techub.api.service;

import com.techub.api.domain.User;
import com.techub.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User cadastrar(User user) {
        return repository.save(user);
    }

    public List<User> listar() {
        return repository.findAll();
    }


}