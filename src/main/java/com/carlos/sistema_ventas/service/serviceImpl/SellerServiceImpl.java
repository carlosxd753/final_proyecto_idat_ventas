package com.carlos.sistema_ventas.service.serviceImpl;

import com.carlos.sistema_ventas.dto.seller.SellerRequest;
import com.carlos.sistema_ventas.dto.seller.SellerResponse;
import com.carlos.sistema_ventas.exception.BusinessException;
import com.carlos.sistema_ventas.exception.ResourceNotFoundException;
import com.carlos.sistema_ventas.mapper.SellerMapper;
import com.carlos.sistema_ventas.model.Seller;
import com.carlos.sistema_ventas.repository.SellerRepository;
import com.carlos.sistema_ventas.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository repository;
    private final SellerMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<SellerResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SellerResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerResponse login(String email, String password) {
        Seller seller = repository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Credenciales incorrectas"));

        if (!passwordEncoder.matches(password, seller.getPassword())) {
            throw new BusinessException("Credenciales incorrectas");
        }

        return mapper.toResponse(seller);
    }

    @Override
    @Transactional
    public SellerResponse create(SellerRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    @Transactional
    public SellerResponse update(Long id, SellerRequest request) {
        Seller entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendedor no encontrado con id: " + id));
        mapper.updateEntity(request, entity);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Vendedor no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}