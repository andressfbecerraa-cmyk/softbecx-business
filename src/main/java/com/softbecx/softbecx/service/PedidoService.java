package com.softbecx.softbecx.service;

import com.softbecx.softbecx.model.DetallePedido;
import com.softbecx.softbecx.model.Pedido;
import com.softbecx.softbecx.repository.DetallePedidoRepository;
import com.softbecx.softbecx.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            DetallePedidoRepository detallePedidoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {

        List<DetallePedido> detalles =
                detallePedidoRepository.findAll();

        for (DetallePedido detalle : detalles) {

            if (detalle.getPedido() != null
                    && detalle.getPedido().getId().equals(id)) {

                detallePedidoRepository.delete(detalle);
            }
        }

        pedidoRepository.deleteById(id);
    }
}