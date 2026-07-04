package org.generation.syntaxlogistics2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.generation.syntaxlogistics2.enums.ServiceType;

// DTO para actualizar una cotización existente
// No incluye userId porque no queremos permitir cambiar el dueño de la cotización al editarla
public record UpdateQuoteRequest(

        @NotBlank(message = "El origen es obligatorio para cotizar")
        String originAddress,

        @NotBlank(message = "El destino es obligatorio para cotizar")
        String destinationAddress,

        @NotNull(message = "El peso del paquete es obligatorio")
        @Positive(message = "El peso debe ser mayor a cero")
        Double weight,

        @NotNull(message = "El largo del paquete es obligatorio")
        @Positive(message = "El largo debe ser mayor a cero")
        Double length,

        @NotNull(message = "El ancho del paquete es obligatorio")
        @Positive(message = "El ancho debe ser mayor a cero")
        Double width,

        @NotNull(message = "La altura es obligatoria")
        @Positive(message = "La altura debe ser mayor a cero")
        Double height,

        @NotNull(message = "El tipo de servicio es obligatorio")
        ServiceType serviceType
) {
}