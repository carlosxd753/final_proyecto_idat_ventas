package com.carlos.sistema_ventas.dto.category;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        LocalDateTime createdAt
) {
}
