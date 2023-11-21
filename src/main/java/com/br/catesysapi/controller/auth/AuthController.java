package com.br.catesysapi.controller.auth;

import com.br.catesysapi.controller.auth.request.LoginDTORequest;
import com.br.catesysapi.controller.auth.response.TokenDTOResponse;
import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@AllArgsConstructor
public class AuthController {
    final AuthService authService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<TokenDTOResponse>> login(@RequestBody LoginDTORequest loginDTORequest) {
        TokenDTOResponse tokenDTOResponse = authService.login(loginDTORequest);

        String message = "Login realizado com sucesso";

        ApiResponseDTO responseDTO = new ApiResponseDTO(tokenDTOResponse, message, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
