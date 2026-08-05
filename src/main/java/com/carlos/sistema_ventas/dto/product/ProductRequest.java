package com.carlos.sistema_ventas.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull
        Integer categoryId,
        @NotBlank
        @Size(max = 150)
        String name,
        @Size(max = 100)
        String brand,
        @NotNull @Min(0)
        Integer stock,
        @DecimalMin("0.00")
        BigDecimal buyPrice,
        @NotNull @DecimalMin("0.00")
        BigDecimal sellPrice
) {
}
