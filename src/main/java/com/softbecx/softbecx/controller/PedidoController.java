package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.model.Pedido;
import com.softbecx.softbecx.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Pedido guardar(@RequestBody Pedido pedido) {
        return pedidoService.guardar(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizar(
            @PathVariable Long id,
            @RequestBody Pedido pedido) {

        Pedido existente =
                pedidoService.buscarPorId(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setCliente(pedido.getCliente());
        existente.setTotal(pedido.getTotal());
        existente.setEstado(pedido.getEstado());

        return pedidoService.guardar(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }
}