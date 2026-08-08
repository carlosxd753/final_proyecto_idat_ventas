package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.dto.quotation.QuotationRequest;
import com.carlos.sistema_ventas.dto.quotation.QuotationResponse;

import java.util.List;

public interface QuotationService {
    List<QuotationResponse> findAll();
    QuotationResponse findById(Long id);
    QuotationResponse create(QuotationRequest request);
    void delete(Long id);
}
