package com.techub.api.repository;

import com.techub.api.domain.Summary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
    @Query("""
    SELECT s FROM Summary s
    WHERE s.student.id IN :followingUsers
       OR s.course.id IN :followingCourses
    ORDER BY s.datahora DESC
    """)
    Page<Summary> findFeedSummaries(
            List<Long> followingUsers,
            List<Long> followingCourses,
            Pageable pageable
    );
}
