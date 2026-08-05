package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuotationRepository extends JpaRepository<Quotation,Long> {
}
