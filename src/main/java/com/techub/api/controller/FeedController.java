package com.techub.api.controller;

import com.techub.api.dto.FeedDTO;
import com.techub.api.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping
    public ResponseEntity<FeedDTO> getFeed(

            @RequestParam Long userId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size
    ) {

        FeedDTO feed = feedService.getFeed(userId, page, size);

        return ResponseEntity.ok(feed);
    }
}