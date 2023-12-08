package com.br.catesysapi.dto;

import com.br.catesysapi.entity.Aluno;
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
