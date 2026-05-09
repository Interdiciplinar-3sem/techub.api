package com.techub.api.repository;

import com.techub.api.domain.Followers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowersRepository
        extends JpaRepository<Followers, Long> {

    List<Followers> findByFollowerId(Long followerId);
}