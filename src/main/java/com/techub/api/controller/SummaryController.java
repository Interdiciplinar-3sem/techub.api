package com.techub.api.controller;

import com.techub.api.domain.Summary;
import com.techub.api.dto.SummaryCreateRequestDTO;
import com.techub.api.dto.SummaryGetResponseDTO;
import com.techub.api.dto.SummaryUpdateRequestDTO;
import com.techub.api.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;


@RestController
@RequestMapping("/resumos")
public class SummaryController {

    @Autowired
    private SummaryService service;

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody SummaryCreateRequestDTO dto) {
        try {
            return ResponseEntity.ok(service.saveSummary(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<SummaryGetResponseDTO> listar() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SummaryGetResponseDTO buscar(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public SummaryGetResponseDTO atualizar(@PathVariable Long id, @RequestBody SummaryUpdateRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Sucesso ao criar ao apagar Resumo");
    }
}