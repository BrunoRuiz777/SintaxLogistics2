package org.generation.syntaxlogistics2.service;

import org.generation.syntaxlogistics2.model.Quote;
import org.generation.syntaxlogistics2.model.ServiceCategory;
import org.generation.syntaxlogistics2.model.Users;
import org.generation.syntaxlogistics2.repository.QuoteRepository;
import org.generation.syntaxlogistics2.repository.ServiceCategoryRepository;
import org.generation.syntaxlogistics2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuoteService {

    // Conexión con la base de datos de cotizaciones
    @Autowired
    private QuoteRepository quoteRepository;

    // Conexión con la tabla de categorías de servicio
    @Autowired
    private ServiceCategoryRepository serviceCategoryRepository;

    // Servicio de usuarios (para saber quién hace la cotización)
    // Repositorio de usuarios (para saber quién hace la cotización)
    @Autowired
    private UsersRepository usersRepository;

    // MÉTODO PRINCIPAL: crear una cotización
    public Quote createQuote(Quote quote, Long userId) {

        // Buscar al usuario que está haciendo la cotización
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        // Buscar la categoría de servicio según el tipo (express, normal, etc.)
        ServiceCategory category = serviceCategoryRepository
                .findByServiceType(quote.getServiceType())
                .orElseThrow(() -> new RuntimeException("Categoría de servicio no configurada"));

        // Asignar el usuario a la cotización
        quote.setUser(user);

        // Guardar el precio base del servicio (de la categoría)
        quote.setBasePrice(category.getBasePrice());

        // CALCULAR EL PRECIO TOTAL
        // Fórmula: precio base + (peso * 10)
        double total = category.getBasePrice() + (quote.getWeight() * 10);

        // Guardar el precio final calculado
        quote.setTotalPrice(total);

        // Fecha estimada de entrega (ejemplo: 3 días después de hoy)
        quote.setEstimatedDeliveryDate(LocalDateTime.now().plusDays(3));

        // La cotización inicia como NO aceptada
        quote.setIsAccepted(false);

        // Guardar todo en la base de datos
        return quoteRepository.save(quote);
    }

    // metodo aceptar una cotización
    public Quote acceptQuote(Long quoteId) {

        // Buscar la cotización por ID
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        //  Cambiar estado a aceptada
        quote.setIsAccepted(true);

        // Guardar cambio en BD
        return quoteRepository.save(quote);

        // Aquí normalmente después se crea el Shipment (envío)
    }

    //metodo buscar cotizaciones por usuario
    public List<Quote> findByUserId(Long userId) {

        // Devuelve todas las cotizaciones de ese usuario
        return quoteRepository.findByUserId(userId);
    }

    // er cotizaciones pendientes
    public List<Quote> findPendingQuotes() {

        // devuelve todas las cotizaciones que NO han sido aceptadas
        return quoteRepository.findByIsAccepted(false);
    }
}
