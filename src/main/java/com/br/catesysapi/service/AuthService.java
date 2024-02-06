package com.br.catesysapi.service;

import com.br.catesysapi.controller.auth.request.LoginDTORequest;
import com.br.catesysapi.controller.auth.response.TokenDTOResponse;
import com.br.catesysapi.domain.entity.Usuario;
import com.br.catesysapi.security.JwtService;
import com.br.catesysapi.security.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    final AuthenticationManager authenticationManager;

    final TokenService tokenService;
    private final JwtService jwtService;

    public TokenDTOResponse login(LoginDTORequest loginDTORequest) {
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTORequest.getUsuario(), loginDTORequest.getSenha());
        var authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

//        var user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        Usuario usuario = (Usuario) authentication.getPrincipal();
        String token = jwtService.generateToken(usuario);

        TokenDTOResponse tokenDTOResponse = new TokenDTOResponse("Bearer", token);

        return tokenDTOResponse;
    }
}
