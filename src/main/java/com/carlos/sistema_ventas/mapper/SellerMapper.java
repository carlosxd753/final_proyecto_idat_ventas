package com.carlos.sistema_ventas.mapper;

import com.carlos.sistema_ventas.dto.seller.SellerRequest;
import com.carlos.sistema_ventas.dto.seller.SellerResponse;
import com.carlos.sistema_ventas.model.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SellerMapper {
    @Mapping(target = "quotations", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Seller toEntity(SellerRequest request);

    SellerResponse toResponse(Seller entity);

    @Mapping(target = "quotations", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(SellerRequest request, @MappingTarget Seller entity);
}