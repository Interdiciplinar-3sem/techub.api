package com.techub.api.dto;

public record SummaryCreateRequestDTO(
        Long studentId,
        String titulo,
        String conteudo
) {}
