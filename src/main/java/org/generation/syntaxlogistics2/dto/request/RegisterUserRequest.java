package org.generation.syntaxlogistics2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(

        @NotBlank(message = "Falta ingresar tu nombre")
        // Solo letras, acentos, ñ y espacios (igual que tu purificarNombre en JS)
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
        // Exactamente 10 dígitos, solo números (igual que tu regex /\D/g + length !== 10)
        @Pattern(regexp = "^[0-9]{10}$", message = "El número de celular debe tener exactamente 10 dígitos")
        String phone,

        @NotBlank(message = "Falta ingresar una contraseña")
        @Size(min = 12, message = "La contraseña es muy corta. Mínimo 12 caracteres")
        @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe incluir al menos una letra mayúscula")
        String password

) {

}
