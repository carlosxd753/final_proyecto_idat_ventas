package com.carlos.sistema_ventas.service.serviceImpl;

import com.carlos.sistema_ventas.dto.product.ProductRequest;
import com.carlos.sistema_ventas.dto.product.ProductResponse;
import com.carlos.sistema_ventas.exception.ResourceNotFoundException;
import com.carlos.sistema_ventas.mapper.ProductMapper;
import com.carlos.sistema_ventas.model.Category;
import com.carlos.sistema_ventas.model.Product;
import com.carlos.sistema_ventas.repository.CategoryRepository;
import com.carlos.sistema_ventas.repository.ProductRepository;
import com.carlos.sistema_ventas.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + request.categoryId()));
        Product product = mapper.toEntity(request);
        product.setCategory(category);
        return mapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + request.categoryId()));
        mapper.updateEntity(request, product);
        product.setCategory(category);
        return mapper.toResponse(productRepository.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con id: " + id);
        }
        productRepository.deleteById(id);
    }
}