package org.generation.syntaxlogistics2.dto.response;

public record LoginResponse(
        String token,
        UserResponse user
) {}