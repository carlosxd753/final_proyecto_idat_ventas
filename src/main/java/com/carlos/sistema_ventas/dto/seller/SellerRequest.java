package com.carlos.sistema_ventas.dto.seller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SellerRequest(
        @NotBlank
        @Size(max = 100)
        String name,
        @Size(max = 255)
        String address,
        @Size(max = 20)
        String phoneNumber,
        @NotBlank
        @Email
        @Size(max = 150)
        String email,
        @NotBlank
        @Size(min = 6, max = 255)
        String password
) {
}
