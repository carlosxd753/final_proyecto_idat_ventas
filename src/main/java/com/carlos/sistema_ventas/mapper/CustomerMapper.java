package com.carlos.sistema_ventas.mapper;

import com.carlos.sistema_ventas.dto.customer.CustomerRequest;
import com.carlos.sistema_ventas.dto.customer.CustomerResponse;
import com.carlos.sistema_ventas.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toEntity(CustomerRequest request);
    CustomerResponse toResponse(Customer entity);
    void updateEntity(CustomerRequest request, @MappingTarget Customer entity);
}
