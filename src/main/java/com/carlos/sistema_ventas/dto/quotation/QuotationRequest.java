package com.carlos.sistema_ventas.dto.quotation;

import com.carlos.sistema_ventas.dto.item_quotation.ItemQuotationRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuotationRequest(
        @NotNull
        Integer sellerId,
        @NotNull
        Integer customerId,
        @NotEmpty
        List<ItemQuotationRequest> items
) {
}
