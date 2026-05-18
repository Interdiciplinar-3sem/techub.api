package com.techub.api.dto;

import com.techub.api.domain.Role;
import io.micrometer.common.lang.Nullable;

import java.util.Date;

public record UserGetResponseDTO (
        Long userId,
        @Nullable Long studentId,
        String email,
        Role role,
        Date createdAt,
        Boolean ativo
){ }
