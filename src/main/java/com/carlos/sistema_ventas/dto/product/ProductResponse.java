package com.carlos.sistema_ventas.dto.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        Long categoryId,
        String categoryName,
        String name,
        String brand,
        Integer stock,
        BigDecimal buyPrice,
        BigDecimal sellPrice,
        LocalDateTime createdAt
) {
}
