package com.techub.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO (
       @NotBlank String nome,
       @NotBlank String email,
       @NotBlank String senha,
       Integer semestre,
       String bio,
       String foto
) {}
