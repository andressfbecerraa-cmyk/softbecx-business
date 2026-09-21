package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.model.Rol;
import com.softbecx.softbecx.service.RolService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public List<Rol> listarRoles() {
        return rolService.listarTodos();
    }

    @GetMapping("/{id}")
    public Rol buscarPorId(@PathVariable Long id) {
        return rolService.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Rol guardar(@RequestBody Rol rol) {
        return rolService.guardar(rol);
    }

    @PutMapping("/{id}")
    public Rol actualizar(@PathVariable Long id, @RequestBody Rol rol) {
        Rol existente = rolService.buscarPorId(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombre(rol.getNombre());
        return rolService.guardar(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        rolService.eliminar(id);
    }
}