package com.carlos.sistema_ventas.dto.customer;

import com.carlos.sistema_ventas.util.DocumentType;

import java.time.LocalDateTime;

public record CustomerResponse(
        Long id,
        String name,
        String address,
        String phoneNumber,
        DocumentType documentType,
        String documentNumber,
        LocalDateTime createdAt
) {
}
