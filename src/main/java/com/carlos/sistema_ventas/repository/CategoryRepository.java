package com.carlos.sistema_ventas.repository;

import com.carlos.sistema_ventas.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
