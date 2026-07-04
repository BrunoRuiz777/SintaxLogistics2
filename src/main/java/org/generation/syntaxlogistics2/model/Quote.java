package org.generation.syntaxlogistics2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.syntaxlogistics2.enums.ServiceType;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quote")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;


    @Column(nullable = false)
    private Double weight;

    //estimado de distancia
    @Column(nullable = false)
    private Double estimatedDistanceKm;

    // fecha de creación
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Fecha estimada en la que le prometemos entregar si acepta la cotización
    @Column(nullable = false)
    private LocalDateTime estimatedDeliveryDate;

    @Column(nullable = false)
    private Double basePrice;

    @Column(nullable = false)
    private Double totalPrice;

    // Saber si el cliente aceptó la cotización para convertirla en un Shipment (Envío)
    @Column(nullable = false)
    private Boolean isAccepted = false;

    // Direcciones de la cotización
    @Column(nullable = false)
    private String originAddress;

    @Column(nullable = false)
    private String destinationAddress;

    // Dimensiones del paquete
    @Column(nullable = false)
    private Double length;

    @Column(nullable = false)
    private Double width;

    @Column(nullable = false)
    private Double height;

    // si llevaba xd jajaja
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;



}