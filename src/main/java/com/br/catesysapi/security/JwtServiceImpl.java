package com.br.catesysapi.security;

import com.br.catesysapi.entity.Usuario;
import com.br.catesysapi.security.context.JwtContext;
import com.br.catesysapi.security.context.UsuarioContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${jwt.secret}")
    private String secret;

    @Value("jwt.expiration")
    private String expiration;

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

//    @Override
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }
    @Override
    public boolean isTokenValid(String token) {
        return isTokenSignatureValid(token) && !isTokenExpired(token);
    }

    @Override
    public UsuarioContext getUsuarioContextFromToken(String token) {
        Claims claims = extractAllClaims(token);
        Map<String, Object> usuarioObject = (Map<String, Object>) claims.get("usuario");

        JwtContext jwtContext = new JwtContext();

        UsuarioContext usuarioContext = new UsuarioContext();
        usuarioContext.setId(Long.parseLong(String.valueOf(usuarioObject.get("id"))));
        usuarioContext.setNome(String.valueOf(usuarioObject.get("nome")));
        usuarioContext.setEmail(String.valueOf(usuarioObject.get("email")));
        usuarioContext.setRoles((List<String>) usuarioObject.get("roles"));

        jwtContext.setUsuario(usuarioContext);

        return usuarioContext;
    }

    private boolean isTokenSignatureValid(String token){
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }






    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    public String generateToken(Usuario usuario) {
        JwtContext jwtContext = generateTokenData(usuario);
        Map<String, Object> claims = objectMapper.convertValue(jwtContext, Map.class);

        return Jwts.builder()
                .setIssuer("CXB_TOKEN")
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()  + Long.parseLong(expiration)))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))).compact();
    }

    private JwtContext generateTokenData(Usuario usuario) {
        JwtContext jwtContext = new JwtContext();

        UsuarioContext usuarioContext = new UsuarioContext();
        usuarioContext.setId(usuario.getId());
        usuarioContext.setEmail(usuario.getPessoa().getEmail());
        usuarioContext.setNome(usuario.getPessoa().getNome());
        List<String> roles = new ArrayList<>();
        usuario.getAuthorities().forEach((grantedAuthority) -> roles.add(grantedAuthority.getAuthority()));
        usuarioContext.setRoles(roles);

        jwtContext.setUsuario(usuarioContext);

        return jwtContext;
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()  + Long.parseLong(expiration)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}