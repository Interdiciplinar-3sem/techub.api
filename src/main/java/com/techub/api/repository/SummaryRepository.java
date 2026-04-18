package com.techub.api.repository;

import com.techub.api.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Summary,Long> {
    //feed geral
    List<Summary> findAllByOrderByCreatedAtDesc();
    //feed de usuario especifico
    List<Summary> findByUserId(Long userId);

    boolean existsByUserIdAndTitle(Long userId, String title);
} //interface para o banco de dados do summary
