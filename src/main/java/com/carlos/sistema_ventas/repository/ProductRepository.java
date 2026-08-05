package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
