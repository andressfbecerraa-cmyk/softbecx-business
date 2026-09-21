package com.softbecx.softbecx.controller;

import com.softbecx.softbecx.dto.LoginRequest;
import com.softbecx.softbecx.dto.RecuperarContrasenaRequest;
import com.softbecx.softbecx.model.Usuario;
import com.softbecx.softbecx.security.JwtService;
import com.softbecx.softbecx.security.TokenBlacklistService;
import com.softbecx.softbecx.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    public LoginController(
            UsuarioService usuarioService,
            JwtService jwtService,
            TokenBlacklistService tokenBlacklistService) {

        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest) {

        Optional<Usuario> usuario =
                usuarioService.login(
                        loginRequest.getCorreo(),
                        loginRequest.getContrasena()
                );

        if (usuario.isPresent()) {

            String token =
                    jwtService.generarToken(usuario.get());

            Map<String, Object> respuesta =
                    new HashMap<>();

            respuesta.put("mensaje", "Login exitoso");
            respuesta.put("token", token);
            respuesta.put("id", usuario.get().getId());
            respuesta.put("nombre", usuario.get().getNombre());
            respuesta.put("correo", usuario.get().getCorreo());
            respuesta.put("rol", usuario.get().getRol().getNombre());

            return ResponseEntity.ok(respuesta);
        }

        Map<String, String> error =
                new HashMap<>();

        error.put(
                "mensaje",
                "Correo o contraseña incorrectos"
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            )
            String authorizationHeader) {

        if (authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ")) {

            Map<String, String> error =
                    new HashMap<>();

            error.put(
                    "mensaje",
                    "Token no proporcionado"
            );

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(error);
        }

        String token =
                authorizationHeader.substring(7);

        tokenBlacklistService.bloquearToken(token);

        Map<String, String> respuesta =
                new HashMap<>();

        respuesta.put(
                "mensaje",
                "Sesión cerrada correctamente"
        );

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<?> recuperarContrasena(
            @RequestBody RecuperarContrasenaRequest request) {

        Optional<String> nuevaContrasena =
                usuarioService.recuperarContrasena(
                        request.getCorreo()
                );

        if (nuevaContrasena.isEmpty()) {

            Map<String, String> error =
                    new HashMap<>();

            error.put(
                    "mensaje",
                    "No existe un usuario con ese correo"
            );

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(error);
        }

        Map<String, String> respuesta =
                new HashMap<>();

        respuesta.put(
                "mensaje",
                "Contraseña recuperada correctamente"
        );

        respuesta.put(
                "nuevaContrasena",
                nuevaContrasena.get()
        );

        return ResponseEntity.ok(respuesta);
    }
}