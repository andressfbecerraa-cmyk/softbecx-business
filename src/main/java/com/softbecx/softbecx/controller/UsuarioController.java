package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.dto.CambiarContrasenaRequest;
import com.softbecx.softbecx.model.Usuario;
import com.softbecx.softbecx.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        Usuario existente =
                usuarioService.buscarPorId(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setNombre(usuario.getNombre());
        existente.setCorreo(usuario.getCorreo());
        existente.setContrasena(usuario.getContrasena());
        existente.setRol(usuario.getRol());

        return usuarioService.guardar(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    @PutMapping("/{id}/cambiar-contrasena")
    public ResponseEntity<?> cambiarContrasena(
            @PathVariable Long id,
            @RequestBody CambiarContrasenaRequest request) {

        boolean cambioExitoso =
                usuarioService.cambiarContrasena(
                        id,
                        request.getContrasenaActual(),
                        request.getNuevaContrasena()
                );

        if (!cambioExitoso) {

            Map<String, String> error = new HashMap<>();

            error.put(
                    "mensaje",
                    "La contraseña actual es incorrecta o el usuario no existe"
            );

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }

        Map<String, String> respuesta = new HashMap<>();

        respuesta.put(
                "mensaje",
                "Contraseña cambiada correctamente"
        );

        return ResponseEntity.ok(respuesta);
    }
}