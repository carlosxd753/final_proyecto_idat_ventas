package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.dto.category.CategoryRequest;
import com.carlos.sistema_ventas.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAll();
    CategoryResponse findById(Long id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void delete(Long id);
}
