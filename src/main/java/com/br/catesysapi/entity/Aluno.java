package com.br.catesysapi.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Pessoa{
    private Long matricula;

    public Aluno(Long id) {
        super(id);
    }

    public Aluno(Long id, String nome, String email, String cpf, LocalDate dataNascimento, String telefone, Long matricula) {
        super(id, nome, email, cpf, dataNascimento, telefone);
        this.matricula = matricula;
    }
}
