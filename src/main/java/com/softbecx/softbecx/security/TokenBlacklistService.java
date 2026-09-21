package com.softbecx.softbecx.security;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    private final Set<String> tokensBloqueados =
            ConcurrentHashMap.newKeySet();

    public void bloquearToken(String token) {
        tokensBloqueados.add(token);
    }

    public boolean estaBloqueado(String token) {
        return tokensBloqueados.contains(token);
    }
}