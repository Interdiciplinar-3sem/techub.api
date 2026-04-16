package com.techub.api.repository;

import com.techub.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);  //se o email ja estiver cadastrado ele nao deixa finalizar o cadastr
}