package com.carlos.sistema_ventas.dto.item_quotation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemQuotationRequest(
        @NotNull
        Long productId,
        @NotNull
        @Min(1)
        Integer quantity
) {
}
