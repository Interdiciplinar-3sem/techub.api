package com.techub.api.service;

import com.techub.api.domain.User;
import com.techub.api.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User criar(User user){
        Optional<User> existUser = userRepository.findByEmail(user.getEmail());
        return existUser.orElseGet(() -> userRepository.save(user));
    }

    @Transactional
    public void deletar(UUID userId){
        userRepository.deleteById(userId);
    }

    @Transactional(readOnly = true)
    public List<User> listar(){
        return userRepository.findAll();
    }

    @Transactional
    public User atualizar(UUID userId, User dto){
        if(userId.equals(dto.getId())){
            return userRepository.save(dto);
        }
        return null;
    }
}
