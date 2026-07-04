package org.generation.syntaxlogistics2.repository;

import org.generation.syntaxlogistics2.enums.ServiceType;
import org.generation.syntaxlogistics2.model.ServiceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Long> {
    // Busca la tarifa del servicio sin necesitar el id numérico
    Optional<ServiceCategory> findByServiceType(ServiceType serviceType);
}