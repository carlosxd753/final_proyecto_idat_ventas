package com.carlos.sistema_ventas.dto.quotation;

import com.carlos.sistema_ventas.dto.item_quotation.ItemQuotationResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record QuotationResponse(
        Long id,
        Long sellerId,
        String sellerName,
        Long customerId,
        String customerName,
        BigDecimal total,
        List<ItemQuotationResponse> items,
        Boolean hasSale,
        LocalDateTime createdAt
) {
}
