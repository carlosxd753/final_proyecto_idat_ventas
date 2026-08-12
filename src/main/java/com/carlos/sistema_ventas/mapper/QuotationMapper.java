package com.carlos.sistema_ventas.mapper;

import com.carlos.sistema_ventas.dto.item_quotation.ItemQuotationResponse;
import com.carlos.sistema_ventas.dto.quotation.QuotationResponse;
import com.carlos.sistema_ventas.model.ItemQuotation;
import com.carlos.sistema_ventas.model.Quotation;
import com.carlos.sistema_ventas.model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuotationMapper {
    @Mapping(source = "seller.id", target = "sellerId")
    @Mapping(source = "seller.name", target = "sellerName")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "sale", target = "hasSale", qualifiedByName = "hasSale")
    QuotationResponse toResponse(Quotation entity);

    List<ItemQuotationResponse> toItemResponseList(List<ItemQuotation> items);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ItemQuotationResponse toItemResponse(ItemQuotation item);

    @org.mapstruct.Named("hasSale")
    default Boolean hasSale(Sale sale) {
        return sale != null;
    }
}