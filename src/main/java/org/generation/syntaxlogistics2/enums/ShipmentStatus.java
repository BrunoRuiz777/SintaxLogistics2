package org.generation.syntaxlogistics2.enums;

public enum ShipmentStatus {
    CREATED, // Cuando el cliente acaba de pagar y generar la guía
    PICKED_UP, // Cuando el repartidor ya lo recolectó en el origen
    IN_TRANSIT, // Cuando el paquete va en camino a su destino
    DELIVERED, // Cuando se entregó exitosamente
    CANCELLED // Por si el cliente cancela el servicio antes de la recolección
}