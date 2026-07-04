package org.generation.syntaxlogistics2.dto.response;

import java.time.LocalDateTime;

public record ShipmentTrackingResponse(
        Long id,
        String trackingNumber,
        String status,
        String originAddress,
        String destinationAddress,
        String recipientName,
        String recipientPhone,
        LocalDateTime createdAt,
        LocalDateTime estimatedDeliveryDate
) {
}