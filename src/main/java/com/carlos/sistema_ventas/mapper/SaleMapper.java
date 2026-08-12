package com.carlos.sistema_ventas.mapper;

import com.carlos.sistema_ventas.dto.sale.SaleResponse;
import com.carlos.sistema_ventas.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "quotation.id", target = "quotationId")
    @Mapping(source = "quotation.customer.name", target = "customerName")
    @Mapping(source = "quotation.seller.name", target = "sellerName")
    SaleResponse toResponse(Sale entity);
}