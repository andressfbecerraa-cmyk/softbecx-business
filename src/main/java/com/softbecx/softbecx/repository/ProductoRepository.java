package com.softbecx.softbecx.repository;

import com.softbecx.softbecx.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}