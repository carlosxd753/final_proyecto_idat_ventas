package com.carlos.sistema_ventas.dto.sale;

import jakarta.validation.constraints.NotNull;

public record SaleRequest(
        @NotNull
        Integer quotationId
) {
}
