package org.generation.syntaxlogistics2.repository;

import org.generation.syntaxlogistics2.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}