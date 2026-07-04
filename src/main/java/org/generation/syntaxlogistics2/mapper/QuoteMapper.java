package org.generation.syntaxlogistics2.mapper;

import org.generation.syntaxlogistics2.dto.request.NewQuoteRequest;
import org.generation.syntaxlogistics2.dto.response.QuoteResponse;
import org.generation.syntaxlogistics2.model.Quote;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class QuoteMapper {

    // De Request (Frontend) a Entidad (Base de Datos)
    public Quote toEntity(NewQuoteRequest request) {
        if (request == null) {
            return null;
        }

        Quote quote = new Quote();

        // Mapeamos todos los atributos exactos de tu NewQuoteRequest
        quote.setOriginAddress(request.originAddress());
        quote.setDestinationAddress(request.destinationAddress());
        quote.setWeight(request.weight());
        quote.setLength(request.length());
        quote.setWidth(request.width());
        quote.setHeight(request.height());
        quote.setServiceType(request.serviceType());

        quote.setEstimatedDistanceKm(0.0);

        return quote;
    }

    // De Entidad (Base de Datos) a DTO (Respuesta)
    public QuoteResponse toResponse(Quote quote) {
        if (quote == null) {
            return null;
        }

        // Calculamos los días estimados (diferencia entre hoy y la fecha calculada en el Service)
        int diasEstimados = 0;
        if (quote.getEstimatedDeliveryDate() != null) {
            diasEstimados = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), quote.getEstimatedDeliveryDate());
        }

        // Usamos el patrón Builder de Lombok (gracias a tu @Builder en QuoteResponse)
        return QuoteResponse.builder()
                .id(quote.getId())
                .price(quote.getTotalPrice())
                .estimatedDays(diasEstimados)
                .status(quote.getIsAccepted() ? "ACCEPTED" : "PENDING")
                .build();
    }
}