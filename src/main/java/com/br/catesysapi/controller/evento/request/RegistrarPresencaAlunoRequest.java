package com.br.catesysapi.controller.evento.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrarPresencaAlunoRequest {
    private Long eventoId;
    private Long alunoId;
    private String alunoNome;
}
