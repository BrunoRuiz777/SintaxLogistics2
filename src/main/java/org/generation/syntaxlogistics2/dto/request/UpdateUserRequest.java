package org.generation.syntaxlogistics2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// DTO para actualizar un usuario existente.
// La password es opcional: si viene vacia/null, el service no la modifica.
public record UpdateUserRequest(

        @NotBlank(message = "Falta ingresar tu nombre")
        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
                message = "El nombre solo puede contener letras y espacios"
        )
        String name,

        @NotBlank(message = "Falta ingresar tus apellidos")
        @Pattern(
                regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$",
                message = "Los apellidos solo pueden contener letras y espacios"
        )
        String lastName,

        @NotBlank(message = "Falta ingresar tu correo electrónico")
        @Email(message = "El formato del correo electrónico no es válido")
        String email,

        @NotBlank(message = "Falta ingresar tu número celular")
        @Pattern(regexp = "^[0-9]{10}$", message = "El número de celular debe tener exactamente 10 dígitos")
        String phone,

        // Opcional: si no quiere cambiar su password, se manda null o ""
        @Pattern(
                regexp = "^$|.{12,}",
                message = "La contraseña es muy corta. Mínimo 12 caracteres"
        )
        String password

) {
}