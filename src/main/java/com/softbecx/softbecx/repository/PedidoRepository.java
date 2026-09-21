package com.softbecx.softbecx.repository;

import com.softbecx.softbecx.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}