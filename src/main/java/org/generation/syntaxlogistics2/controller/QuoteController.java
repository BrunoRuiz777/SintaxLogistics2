package org.generation.syntaxlogistics2.controller;

import jakarta.validation.Valid;
import org.generation.syntaxlogistics2.dto.request.NewQuoteRequest;
import org.generation.syntaxlogistics2.dto.request.UpdateQuoteRequest;
import org.generation.syntaxlogistics2.dto.response.QuoteResponse;
import org.generation.syntaxlogistics2.mapper.QuoteMapper;
import org.generation.syntaxlogistics2.model.Quote;
import org.generation.syntaxlogistics2.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private QuoteMapper quoteMapper;

    //POST /api/quotes
    @PostMapping
    public ResponseEntity<QuoteResponse> create(@Valid @RequestBody NewQuoteRequest request) {

        // SIN la línea "QuoteMapper quoteMapper;" aquí
        Quote quote = quoteMapper.toEntity(request);
        Quote saved = quoteService.createQuote(quote, request.userId());
        return ResponseEntity.ok(quoteMapper.toResponse(saved));
    }

    // PUT /api/quotes/{id}/accept
    @PutMapping("/{id}/accept")
    public ResponseEntity<QuoteResponse> accept(@PathVariable Long id) {
        Quote accepted = quoteService.acceptQuote(id);
        return ResponseEntity.ok(quoteMapper.toResponse(accepted));
    }

    // GET /api/quotes -> ver todas las cotizaciones
    @GetMapping
    public ResponseEntity<List<QuoteResponse>> getAll() {
        List<Quote> quotes = quoteService.findAll();
        List<QuoteResponse> response = quotes.stream()
                .map(quoteMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // GET /api/quotes/{id} -> ver una cotización específica
    @GetMapping("/{id}")
    public ResponseEntity<QuoteResponse> getById(@PathVariable Long id) {
        Quote quote = quoteService.findById(id);
        return ResponseEntity.ok(quoteMapper.toResponse(quote));
    }

    // GET /api/quotes/user/{userId} -> ver cotizaciones de un usuario específico
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuoteResponse>> getByUser(@PathVariable Long userId) {
        List<Quote> quotes = quoteService.findByUserId(userId);
        List<QuoteResponse> response = quotes.stream()
                .map(quoteMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // GET /api/quotes/pending -> ver cotizaciones que aún no han sido aceptadas
    @GetMapping("/pending")
    public ResponseEntity<List<QuoteResponse>> getPending() {
        List<Quote> quotes = quoteService.findPendingQuotes();
        List<QuoteResponse> response = quotes.stream()
                .map(quoteMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    // PUT /api/quotes/{id} -> actualizar los datos de una cotización
    @PutMapping("/{id}")
    public ResponseEntity<QuoteResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateQuoteRequest request) {
        Quote updated = quoteService.updateQuote(id, request);
        return ResponseEntity.ok(quoteMapper.toResponse(updated));
    }

    // DELETE /api/quotes/{id} -> eliminar una cotización
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.noContent().build();
    }
}