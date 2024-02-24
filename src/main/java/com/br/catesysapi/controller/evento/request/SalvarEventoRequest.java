package com.br.catesysapi.controller.evento.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalvarEventoRequest {
    private Long id;
    private String tipo;
    private LocalDateTime horario;
}
