package org.generation.syntaxlogistics2.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shipments")
@AllArgsConstructor
public class ShipmentController {

    @PostMapping
    public ResponseEntity<String> createShipment() {
        return ResponseEntity.ok("Envío creado");
    }
}
