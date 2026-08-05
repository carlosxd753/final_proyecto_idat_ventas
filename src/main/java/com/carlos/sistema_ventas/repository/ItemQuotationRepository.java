package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.ItemQuotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemQuotationRepository extends JpaRepository<ItemQuotation, Long> {
}
