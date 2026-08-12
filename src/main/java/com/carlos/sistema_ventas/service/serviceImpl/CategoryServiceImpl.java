package com.carlos.sistema_ventas.service.serviceImpl;

import com.carlos.sistema_ventas.dto.category.CategoryRequest;
import com.carlos.sistema_ventas.dto.category.CategoryResponse;
import com.carlos.sistema_ventas.exception.ResourceNotFoundException;
import com.carlos.sistema_ventas.mapper.CategoryMapper;
import com.carlos.sistema_ventas.model.Category;
import com.carlos.sistema_ventas.repository.CategoryRepository;
import com.carlos.sistema_ventas.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
        mapper.updateEntity(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoría no encontrada con id: " + id);
        }
        repository.deleteById(id);
    }
}
