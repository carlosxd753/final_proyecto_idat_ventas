package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.dto.sale.SaleRequest;
import com.carlos.sistema_ventas.dto.sale.SaleResponse;

import java.util.List;

public interface SaleService {
    List<SaleResponse> findAll();
    SaleResponse findById(Long id);
    SaleResponse create(SaleRequest request);
}
