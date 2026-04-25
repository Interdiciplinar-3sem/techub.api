package com.techub.api.dto;

public record ErrorResponse (
        int status,
        String code,
        String message
) {}
