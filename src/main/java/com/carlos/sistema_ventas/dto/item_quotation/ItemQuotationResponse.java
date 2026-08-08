package com.carlos.sistema_ventas.dto.item_quotation;

import java.math.BigDecimal;

public record ItemQuotationResponse(
        Long id,
        Long productId,
        String productName,
        Integer quantity,
        BigDecimal unitPrice,
        BigDecimal subtotal
) {
}
