package com.carlos.sistema_ventas.service;

public interface CustomerService {
    List<CustomerResponse> findAll();
    CustomerResponse findById(Integer id);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(Integer id, CustomerRequest request);
    void delete(Integer id);
}
