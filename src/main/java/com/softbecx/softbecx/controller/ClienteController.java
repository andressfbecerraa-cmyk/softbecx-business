package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.model.Cliente;
import com.softbecx.softbecx.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente) {

        Cliente existente =
                clienteService.buscarPorId(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombre(cliente.getNombre());
        existente.setDocumento(cliente.getDocumento());
        existente.setTelefono(cliente.getTelefono());
        existente.setCorreo(cliente.getCorreo());
        existente.setDireccion(cliente.getDireccion());

        return clienteService.guardar(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
    }
}