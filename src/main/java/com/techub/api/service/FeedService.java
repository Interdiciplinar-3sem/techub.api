package com.techub.api.service;

import com.techub.api.domain.Summary;
import com.techub.api.dto.FeedDTO;
import com.techub.api.repository.SummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

        private final SummaryRepository summaryRepository;
        private final FollowService followService;

        public FeedService(SummaryRepository summaryRepository,
                           FollowService followService) {
            this.summaryRepository = summaryRepository;
            this.followService = followService;
        }

        public FeedDTO getFeed(Long userId, int page, int size) {

            // 1. Quem o usuário segue
            List<Long> followingUsers = followService.getFollowingUsers(userId);
            List<Long> followingCourses = followService.getFollowingCourses(userId);


            // 2. Evita erro com lista vazia
            if (followingUsers.isEmpty() && followingCourses.isEmpty()) {
                return new FeedDTO(List.of(), page, size, 0);
            }

            Pageable pageable = PageRequest.of(page, size);

            // 3. Busca no banco
            Page<Summary> summaries = summaryRepository.findFeedSummaries(
                    followingUsers,
                    followingCourses,
                    pageable
            );

            // 4. Retorna DTO
            return new FeedDTO(
                    summaries.getContent(),
                    summaries.getNumber(),
                    summaries.getSize(),
                    summaries.getTotalElements()
            );
        }
}

