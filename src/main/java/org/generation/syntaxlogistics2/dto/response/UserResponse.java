package org.generation.syntaxlogistics2.dto.response;

public record UserResponse(
        Long id,
        String name,
        String lastName,
        String email,
        String phone,
        String role
) {}
