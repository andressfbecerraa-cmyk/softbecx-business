package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.model.DetallePedido;
import com.softbecx.softbecx.service.DetallePedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(
            DetallePedidoService detallePedidoService) {

        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public DetallePedido buscarPorId(@PathVariable Long id) {
        return detallePedidoService
                .buscarPorId(id)
                .orElse(null);
    }

    @GetMapping("/pedido/{pedidoId}")
    public List<DetallePedido> buscarPorPedido(
            @PathVariable Long pedidoId) {

        return detallePedidoService
                .buscarPorPedido(pedidoId);
    }

    @PostMapping
    public DetallePedido guardar(
            @RequestBody DetallePedido detallePedido) {

        return detallePedidoService.guardar(detallePedido);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizar(
            @PathVariable Long id,
            @RequestBody DetallePedido detallePedido) {

        DetallePedido existente =
                detallePedidoService
                        .buscarPorId(id)
                        .orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setPedido(detallePedido.getPedido());
        existente.setProducto(detallePedido.getProducto());
        existente.setCantidad(detallePedido.getCantidad());
        existente.setPrecio(detallePedido.getPrecio());

        return detallePedidoService.guardar(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        detallePedidoService.eliminar(id);
    }
}