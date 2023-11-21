package com.br.catesysapi.controller.auth.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTORequest {
    private String usuario;
    private String senha;
}
