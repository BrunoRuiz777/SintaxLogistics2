package org.generation.syntaxlogistics2.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteResponse {
    private Long id;
    private Double price;
    private Integer estimatedDays;
    private String status;
}