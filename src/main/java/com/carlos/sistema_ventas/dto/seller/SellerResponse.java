package com.carlos.sistema_ventas.dto.seller;

import java.time.LocalDateTime;

public record SellerResponse(
        Long id,
        String name,
        String address,
        String phoneNumber,
        String email,
        LocalDateTime createdAt
) {
}
