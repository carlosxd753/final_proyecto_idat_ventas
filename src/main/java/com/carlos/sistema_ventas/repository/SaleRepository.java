package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
