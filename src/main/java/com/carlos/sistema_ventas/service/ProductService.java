package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.dto.product.ProductRequest;
import com.carlos.sistema_ventas.dto.product.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void delete(Long id);
}
