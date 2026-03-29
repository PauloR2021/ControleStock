package com.paulo.controlstock.dtos.user;

import com.paulo.controlstock.models.user.UserRole;

public record RequestUserDTO(
        String name,
        String username,
        String password,
        UserRole role
) {
}
