package org.generation.syntaxlogistics2.model;
//Usuario es para ver como se ve en la Mysql, dice bruno

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.generation.syntaxlogistics2.enums.UserRole;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 10)
    private String phoneNumber;

    private LocalDateTime registrationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole rol;

    // Un usuario puede tener muchos envíos o cotizaciones.
    // @OneToMany indica una relación de uno a muchos.
    // mappedBy = "usuario" significa que la relación se administra desde la clase Shipment,
    // en el atributo llamado "usuario", por lo que esta clase no crea otra llave foránea.
    // cascade = CascadeType.ALL hace que las operaciones (guardar, actualizar y eliminar)
    // realizadas sobre el usuario también se apliquen a sus envíos.
    // fetch = FetchType.LAZY indica que los envíos no se cargan inmediatamente;
    // solo se obtienen de la base de datos cuando se solicitan.
    // La lista almacena todos los envíos que pertenecen a este usuario.

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Shipment> shipments = new ArrayList<>();

    // Un usuario tiene muchas cotizaciones
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Quote> quotes = new ArrayList<>();
}
