package org.generation.syntaxlogistics2.controller;

import jakarta.validation.Valid;
import org.generation.syntaxlogistics2.dto.request.NewQuoteRequest;
import org.generation.syntaxlogistics2.dto.response.QuoteResponse;
import org.generation.syntaxlogistics2.mapper.QuoteMapper;
import org.generation.syntaxlogistics2.model.Quote;
import org.generation.syntaxlogistics2.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
