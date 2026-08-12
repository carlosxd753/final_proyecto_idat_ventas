package com.carlos.sistema_ventas.mapper;

import com.carlos.sistema_ventas.dto.category.CategoryRequest;
import com.carlos.sistema_ventas.dto.category.CategoryResponse;
import com.carlos.sistema_ventas.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);
    CategoryResponse toResponse(Category entity);
    void updateEntity(CategoryRequest request, @MappingTarget Category entity);
}