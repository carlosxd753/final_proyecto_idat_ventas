package com.carlos.sistema_ventas.dto.sale;

import java.time.LocalDateTime;

public record SaleResponse(
        Long id,
        Long quotationId,
        String customerName,
        String sellerName,
        LocalDateTime createdAt
) {
}
