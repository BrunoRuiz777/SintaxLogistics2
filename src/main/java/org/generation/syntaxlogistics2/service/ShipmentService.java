package org.generation.syntaxlogistics2.service;

import org.generation.syntaxlogistics2.enums.ShipmentStatus;
import org.generation.syntaxlogistics2.model.Shipment;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.ShipmentRepository;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.generation.syntaxlogistics2.util.TrackingNumberGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShipmentService { // <-- Nota que ya es una clase normal, no interfaz

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Shipment createShipment(Shipment shipment, Long userId) {

        // Buscamos que el usuario exista
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        shipment.setUser(user);

        // Generamos la guía (El generador que hicimos hace rato)
        String trackingNumber;
        do {
            trackingNumber = TrackingNumberGeneratorUtil.generateTrackingNumber();
        } while (shipmentRepository.findByTrackingNumber(trackingNumber).isPresent());

        // Asignamos datos base
        shipment.setTrackingNumber(trackingNumber);
        shipment.setStatus(ShipmentStatus.CREATED);
        shipment.setCreatedAt(LocalDateTime.now());

        return shipmentRepository.save(shipment);
    }
}