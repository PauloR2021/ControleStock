package com.paulo.controlstock.dtos.user;

public record ResponseUserDTO(
        Integer id,
        String name,
        String username,
        String role
) {
}
