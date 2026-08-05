package com.carlos.sistema_ventas.dto.customer;

import com.carlos.sistema_ventas.util.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomerRequest(
        @NotBlank
        @Size(max = 100)
        String name,
        @Size(max = 255)
        String address,
        @Size(max = 20)
        String phoneNumber,
        DocumentType documentType,
        @Size(max = 50)
        String documentNumber
) {
}
