package com.br.catesysapi.controller.auth.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDTOResponse {
    String type;
    String token;

    public TokenDTOResponse() {
    }

    public TokenDTOResponse(String type, String token) {
        this.type = type;
        this.token = token;
    }
}