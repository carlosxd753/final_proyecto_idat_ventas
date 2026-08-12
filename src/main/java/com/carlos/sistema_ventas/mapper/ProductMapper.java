package com.carlos.sistema_ventas.mapper;

import com.carlos.sistema_ventas.dto.product.ProductRequest;
import com.carlos.sistema_ventas.dto.product.ProductResponse;
import com.carlos.sistema_ventas.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "quotationItems", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product entity);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "quotationItems", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntity(ProductRequest request, @MappingTarget Product entity);
}