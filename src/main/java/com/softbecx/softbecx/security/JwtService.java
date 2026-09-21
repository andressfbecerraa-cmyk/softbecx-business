package com.softbecx.softbecx.security;

import com.softbecx.softbecx.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "SoftBecX-Clave-Secreta-Segura-2026-Para-JWT";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24;

    private final SecretKey key;

    public JwtService() {
        this.key = Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generarToken(Usuario usuario) {

        Date ahora = new Date();

        Date expiracion = new Date(
                ahora.getTime() + EXPIRATION_TIME
        );

        return Jwts.builder()
                .subject(usuario.getCorreo())
                .claim("id", usuario.getId())
                .claim("nombre", usuario.getNombre())
                .claim("rol", usuario.getRol().getNombre())
                .issuedAt(ahora)
                .expiration(expiracion)
                .signWith(key)
                .compact();
    }
}