package org.generation.syntaxlogistics2.repository;

import org.generation.syntaxlogistics2.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber); // esta ya la tenías (la usa tu tracking generator)

    List<Shipment> findByUserIdOrderByCreatedAtDesc(Long userId); // ⬅️ esta es la que falta agregar
}