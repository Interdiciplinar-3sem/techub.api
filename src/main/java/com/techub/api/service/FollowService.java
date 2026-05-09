package com.techub.api.service;

import com.techub.api.domain.Course;
import com.techub.api.domain.Followers;
import com.techub.api.repository.FollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowersRepository followersRepository;

    // Usuários que o estudante segue
    public List<Long> getFollowingUsers(Long studentId) {

        List<Followers> follows =
                followersRepository.findByFollowerId(studentId);

        return follows.stream()
                .map(follow -> follow.getFollowing().getId())
                .toList();
    }

    // Cursos que o estudante segue
    public List<Long> getFollowingCourses(Long studentId) {

        List<Followers> follows =
                followersRepository.findByFollowerId(studentId);

        return follows.stream()
                .map(follow -> follow.getFollowing().getCursoAtual().getId())
                .distinct()
                .toList();
    }
}