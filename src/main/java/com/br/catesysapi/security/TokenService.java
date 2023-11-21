package com.br.catesysapi.security;

import com.br.catesysapi.entity.Usuario;
import com.br.catesysapi.security.context.JwtContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class TokenService {
    ObjectMapper objectMapper = new ObjectMapper();
    private String expiration ;
    private String secret;

    public String generateJwtToken(Usuario usuario) {
        JwtContext jwtContext =  new JwtContext().generate(usuario);

        Date now = new Date();

        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        return Jwts.builder()
                .setIssuer("HO_TOKEN")
                .setClaims(objectMapper.convertValue(jwtContext, Map.class))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Long.parseLong(expiration)))
                .signWith(secretKey).compact();
    }

}
