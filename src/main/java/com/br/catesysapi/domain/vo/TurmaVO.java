package com.br.catesysapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TurmaVO {
    private Long id;
    private Object professor;
    private String diaSemana;
    private LocalTime horario;
    private List<AlunoVO> alunos;
}
