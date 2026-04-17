package com.techub.api.dto;

import com.techub.api.domain.Role;

public record UserRoleResponse (
    Long id,
    Role role
) {}
