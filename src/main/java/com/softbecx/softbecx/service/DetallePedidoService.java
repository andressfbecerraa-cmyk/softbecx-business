package com.softbecx.softbecx.service;

import com.softbecx.softbecx.model.DetallePedido;
import com.softbecx.softbecx.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoService(
            DetallePedidoRepository detallePedidoRepository) {

        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<DetallePedido> listarTodos() {
        return detallePedidoRepository.findAll();
    }

    public Optional<DetallePedido> buscarPorId(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public List<DetallePedido> buscarPorPedido(Long pedidoId) {
        return detallePedidoRepository.findByPedidoId(pedidoId);
    }

    public DetallePedido guardar(DetallePedido detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }

    public void eliminar(Long id) {
        detallePedidoRepository.deleteById(id);
    }
}