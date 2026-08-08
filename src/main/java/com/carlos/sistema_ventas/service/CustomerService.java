package com.carlos.sistema_ventas.service;

import com.carlos.sistema_ventas.dto.customer.CustomerRequest;
import com.carlos.sistema_ventas.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(Long id);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Long id, CustomerRequest request);
    void delete(Long id);
}
