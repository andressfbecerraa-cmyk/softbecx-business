package com.softbecx.softbecx.service;

import com.softbecx.softbecx.model.Usuario;
import com.softbecx.softbecx.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardar(Usuario usuario) {
        usuario.setContrasena(
                passwordEncoder.encode(usuario.getContrasena())
        );

        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> login(String correo, String contrasena) {

        Optional<Usuario> usuario =
                usuarioRepository.findByCorreo(correo);

        if (usuario.isPresent()
                && passwordEncoder.matches(
                contrasena,
                usuario.get().getContrasena())) {

            return usuario;
        }

        return Optional.empty();
    }

    public boolean cambiarContrasena(
            Long id,
            String contrasenaActual,
            String nuevaContrasena) {

        Optional<Usuario> usuarioOptional =
                usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            return false;
        }

        Usuario usuario = usuarioOptional.get();

        boolean contrasenaCorrecta =
                passwordEncoder.matches(
                        contrasenaActual,
                        usuario.getContrasena()
                );

        if (!contrasenaCorrecta) {
            return false;
        }

        usuario.setContrasena(
                passwordEncoder.encode(nuevaContrasena)
        );

        usuarioRepository.save(usuario);

        return true;
    }

    public Optional<String> recuperarContrasena(String correo) {

        Optional<Usuario> usuarioOptional =
                usuarioRepository.findByCorreo(correo);

        if (usuarioOptional.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuario = usuarioOptional.get();

        String nuevaContrasena =
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8);

        usuario.setContrasena(
                passwordEncoder.encode(nuevaContrasena)
        );

        usuarioRepository.save(usuario);

        return Optional.of(nuevaContrasena);
    }
}