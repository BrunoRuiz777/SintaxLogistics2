package org.generation.syntaxlogistics2.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.generation.syntaxlogistics2.enums.ServiceType;

public record CreateShipmentRequest(

        @NotNull(message = "El ID del remitente es obligatorio")
        Long senderId,

        @NotBlank(message = "La dirección de origen es obligatoria")
        String originAddress,

        @NotBlank(message = "La dirección de destino es obligatoria")
        String destinationAddress,

        @NotBlank(message = "El nombre del remitente es obligatorio")
        String senderName,

        @NotBlank(message = "El teléfono del remitente es obligatorio")
        String senderPhone,

        @NotBlank(message = "El nombre del destinatario es obligatorio")
        String recipientName,

        @NotBlank(message = "El teléfono del destinatario es obligatorio")
        String recipientPhone,

        @NotBlank(message = "El nombre de quien recibe es obligatorio")
        String receiverName,

        @NotBlank(message = "El teléfono de quien recibe es obligatorio")
        String receiverPhone,

        @NotBlank(message = "La descripción del paquete es obligatoria")
        String packageDescription,

        @NotNull(message = "El peso es obligatorio")
        @Positive(message = "El peso debe ser mayor a cero")
        Double weight,

        @NotNull(message = "El largo es obligatorio")
        @Positive(message = "El largo debe ser mayor a cero")
        Double length,

        @NotNull(message = "El ancho es obligatorio")
        @Positive(message = "El ancho debe ser mayor a cero")
        Double width,

        @NotNull(message = "La altura es obligatoria")
        @Positive(message = "La altura debe ser mayor a cero")
        Double height,

        @NotNull(message = "El tipo de servicio es obligatorio")
        ServiceType serviceType
    ) {

}
