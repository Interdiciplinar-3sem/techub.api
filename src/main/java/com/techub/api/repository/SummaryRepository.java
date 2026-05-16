package com.techub.api.repository;

import com.techub.api.domain.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    List<Summary> findByAtivoTrue();
    List<Summary> findByAtivoFalse();
}
