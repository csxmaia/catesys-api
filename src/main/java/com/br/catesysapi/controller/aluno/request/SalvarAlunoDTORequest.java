package com.br.catesysapi.controller.aluno.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalvarAlunoDTORequest {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
}
