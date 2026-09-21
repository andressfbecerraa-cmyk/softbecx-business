package com.softbecx.softbecx.repository;

import com.softbecx.softbecx.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}