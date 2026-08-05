package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
