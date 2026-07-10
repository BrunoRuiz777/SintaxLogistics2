package org.generation.syntaxlogistics2.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.generation.syntaxlogistics2.dto.request.CreateShipmentRequest;
import org.generation.syntaxlogistics2.model.Shipment;
import org.generation.syntaxlogistics2.service.ShipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
@AllArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    // Crear un nuevo envío: POST /api/shipments/{userId}
    @PostMapping("/{userId}")
    public ResponseEntity<Shipment> createShipment(
            @RequestBody @Valid CreateShipmentRequest request,
            @PathVariable Long userId) {

        Shipment newShipment = shipmentService.createShipment(request, userId);
        return new ResponseEntity<>(newShipment, HttpStatus.CREATED);
    }

    // Traer el historial de un usuario: GET /api/shipments/user/{userId}
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Shipment>> getShipmentsByUser(@PathVariable Long userId) {
        List<Shipment> shipments = shipmentService.getShipmentsByUserId(userId);
        return ResponseEntity.ok(shipments);
    }
}