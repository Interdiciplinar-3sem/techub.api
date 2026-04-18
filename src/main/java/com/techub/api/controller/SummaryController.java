package com.techub.api.controller;

import com.techub.api.domain.Summary;
import com.techub.api.dto.SummaryRequest;
import com.techub.api.dto.SummaryResponse;
import com.techub.api.service.SummaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/summaries")
public class SummaryController {
    private final SummaryService service;

    public  SummaryController(SummaryService service) {
        this.service = service;
    }
    @GetMapping ("/feed")
    public List<SummaryResponse> getFeed(){
        return service.getFeed();
    }
    @GetMapping("/user/{id}")
    public List<Summary> getByUser(@PathVariable Long id){
        return service.getSummariesByUser(id);
    }

    @PostMapping
    public Summary create(@RequestBody SummaryRequest request) {
        return service.createSummary(request);
    }

}
