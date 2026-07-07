package org.generation.syntaxlogistics2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Falta ingresar tu correo electrónico")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "Falta ingresar tu contraseña")
        String password
) {}