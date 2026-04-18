package com.techub.api.service;

import com.techub.api.domain.Summary;
import com.techub.api.domain.User;
import com.techub.api.dto.SummaryRequest;
import com.techub.api.dto.SummaryResponse;
import com.techub.api.repository.SummaryRepository;
import com.techub.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {
    private final SummaryRepository repository;
    private  final UserRepository userRepository;

    public SummaryService(SummaryRepository repository, UserRepository userRepository) {

        this.repository = repository;
        this.userRepository = userRepository;
    }

    //criado metodo mostar no feed
    public List<SummaryResponse> getFeed() {
        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(summary -> {
                    SummaryResponse dto = new SummaryResponse();
                    dto.setTitle(summary.getTitle());
                    dto.setContent(summary.getContent());
                    dto.setUserName(summary.getUser().getEmail());
                    return dto;
                })
                .toList();
    }

    public Summary createSummary(SummaryRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        boolean exists = repository.existsByUserIdAndTitle(
                user.getId(),
                request.getTitle()
        );
        if (exists) {
            throw new RuntimeException("Você já criou um resumo com esse título");
        }

        Summary summary = new Summary();
        summary.setTitle(request.getTitle());
        summary.setContent(request.getContent());
        summary.setUser(user);

        return repository.save(summary);
    }

    public List<Summary> getSummariesByUser(Long userId){
        return repository.findByUserId(userId);
    }

}
