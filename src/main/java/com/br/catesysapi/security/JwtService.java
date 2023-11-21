package com.br.catesysapi.security;

import com.br.catesysapi.security.context.UsuarioContext;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

//    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenValid(String token);
    UsuarioContext getUsuarioContextFromToken(String token);
}