package org.generation.syntaxlogistics2.service;

import org.generation.syntaxlogistics2.dto.request.CreateShipmentRequest;
import org.generation.syntaxlogistics2.enums.ShipmentStatus;
import org.generation.syntaxlogistics2.mapper.ShipmentMapper;
import org.generation.syntaxlogistics2.model.Shipment;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.ShipmentRepository;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.generation.syntaxlogistics2.util.TrackingNumberGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ShipmentMapper shipmentMapper;

    public Shipment createShipment(CreateShipmentRequest request, Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        // Usamos el mapper que ya tenías para armar la entidad desde el DTO
        Shipment shipment = shipmentMapper.toEntity(request);
        shipment.setUser(user);

        // Generamos la guía (tu lógica original, intacta)
        String trackingNumber;
        do {
            trackingNumber = TrackingNumberGeneratorUtil.generateTrackingNumber();
        } while (shipmentRepository.findByTrackingNumber(trackingNumber).isPresent());

        shipment.setTrackingNumber(trackingNumber);
        shipment.setStatus(ShipmentStatus.CREATED);
        shipment.setCreatedAt(LocalDateTime.now());

        // ⚠️ AJUSTA la cantidad de días según la lógica real de tu negocio
        shipment.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(3));

        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getShipmentsByUserId(Long userId) {
        return shipmentRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}