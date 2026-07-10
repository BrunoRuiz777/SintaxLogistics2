package org.generation.syntaxlogistics2.mapper;

import org.generation.syntaxlogistics2.dto.request.CreateShipmentRequest;
import org.generation.syntaxlogistics2.dto.response.ShipmentTrackingResponse;
import org.generation.syntaxlogistics2.model.Shipment;
import org.springframework.stereotype.Component;

@Component
public class ShipmentMapper {

    // De Request (Frontend) a Entidad (Base de Datos)
    public Shipment toEntity(CreateShipmentRequest request) {
        Shipment shipment = new Shipment();

        shipment.setOriginAddress(request.originAddress());
        shipment.setDestinationAddress(request.destinationAddress());

        shipment.setSenderName(request.senderName());
        shipment.setSenderPhone(request.senderPhone());
        shipment.setRecipientName(request.recipientName());
        shipment.setRecipientPhone(request.recipientPhone());
        shipment.setReceiverName(request.receiverName());
        shipment.setReceiverPhone(request.receiverPhone());

        shipment.setPackageDescription(request.packageDescription());
        shipment.setWeight(request.weight());
        shipment.setLength(request.length());
        shipment.setWidth(request.width());
        shipment.setHeight(request.height());

        shipment.setServiceType(request.serviceType());

        return shipment;
    }

    public ShipmentTrackingResponse toResponse(Shipment shipment) {
        return new ShipmentTrackingResponse(
                shipment.getId(),
                shipment.getTrackingNumber(),
                shipment.getStatus().name(),
                shipment.getOriginAddress(),
                shipment.getDestinationAddress(),
                shipment.getRecipientName(),
                shipment.getRecipientPhone(),
                shipment.getCreatedAt(),
                shipment.getEstimatedDeliveryDate()
        );
    }
}