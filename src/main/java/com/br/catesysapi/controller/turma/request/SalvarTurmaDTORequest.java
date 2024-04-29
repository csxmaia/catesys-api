package com.br.catesysapi.controller.turma.request;

import com.br.catesysapi.domain.vo.AlunoVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalvarTurmaDTORequest {
    private Long id;
    private String etapa;
    private Long professorId;
    private String diaSemana;
    private String horario;
    private List<AlunoVO> alunos;
}
