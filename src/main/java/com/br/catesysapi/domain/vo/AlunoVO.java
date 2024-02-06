package com.br.catesysapi.domain.vo;

import com.br.catesysapi.domain.entity.Aluno;
import com.br.catesysapi.dto.PessoaVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoVO extends PessoaVO {
    private Long matricula;

    public Aluno toEntity() {
        Aluno aluno = new Aluno(getId(), getNome(), getEmail(), getCpf(), getDataNascimento(), getTelefone(), matricula);
        return aluno;
    }
}
