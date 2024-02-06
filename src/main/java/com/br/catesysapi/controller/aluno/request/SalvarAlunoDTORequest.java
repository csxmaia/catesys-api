package com.br.catesysapi.controller.aluno.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalvarAlunoDTORequest {
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private LocalDate batizadoData;
    private String batizadoParoquia;
    private String condicaoSaudeComportamental;
    private String rua;
    private String numero;
    private String bairro;
    private String complemento;
    private String cidade;
    private Long turmaId;
}
