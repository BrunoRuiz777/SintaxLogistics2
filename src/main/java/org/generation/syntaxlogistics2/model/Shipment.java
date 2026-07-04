package org.generation.syntaxlogistics2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.syntaxlogistics2.enums.ServiceType;
import org.generation.syntaxlogistics2.enums.ShipmentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "Shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String trackingNumber;

    @Column(nullable = false, length = 200)
    private String originAddress;

    @Column(nullable = false, length = 200)
    private String destinationAddress;

    @Column(nullable = false) //el peso es obligatorio
    private Double weight;

    @Column(nullable = false) //el largo es obligatorio
    private Double length;

    @Column(nullable = false) //el ancho es obligatorio
    private Double width;

    @Column(nullable = false) //la altura es obligatorio
    private Double height;

    // Fecha en la que se generó la guía
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // Quién recibe?
    @Column(nullable = false, length = 100)
    private String receiverName;

    @Column(nullable = false, length = 15)
    private String receiverPhone;

    // Quién envía?
    @Column(nullable = false, length = 100)
    private String senderName;

    @Column(nullable = false, length = 15)
    private String senderPhone;

    // descripción de contenido
    @Column(nullable = false, length = 255)
    private String contentDescription;

    // Enum para saber si está CREADO, EN_RUTA, o ENTREGAO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    // La fecha que se le prometió al cliente
    @Column(nullable = false)
    private LocalDateTime estimatedDeliveryDate;

    // La fecha REAL en la que el repartidor entregó el paquete.
    // OJO: nullable = true porque al crear el envío aún no se ha entregado, estará vacío al principio.
    @Column
    private LocalDateTime actualDeliveryDate;

    // se conecta con user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
