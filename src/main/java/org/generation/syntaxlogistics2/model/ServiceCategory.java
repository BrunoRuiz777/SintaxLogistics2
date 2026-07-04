package org.generation.syntaxlogistics2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.syntaxlogistics2.enums.ServiceType;

@Entity
@Table (name = "CategoryService")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,  length = 50)
    private String serviceName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @Column(nullable = false)
    private Double basePrice; //es precio dinamico y se hara dinamico cunado estemos editando en service
}