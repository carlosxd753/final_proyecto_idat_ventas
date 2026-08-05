package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
