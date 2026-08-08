package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.dto.seller.SellerRequest;
import com.carlos.sistema_ventas.dto.seller.SellerResponse;

import java.util.List;

public interface SellerService {
    List<SellerResponse> findAll();
    SellerResponse findById(Long id);
    SellerResponse create(SellerRequest request);
    SellerResponse update(Long id, SellerRequest request);
    void delete(Long id);
}
