package com.br.catesysapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Pessoa{
    @Column
    private Long matricula;
    @Column
    private LocalDate batizadoData;
    @Column
    private String batizadoParoquia;
    @Column
    private String condicaoSaudeComportamental;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id")
    private Turma turma;


    public boolean isBatizado() {
        if(batizadoData != null || batizadoParoquia != null) {
            return true;
        }
        return false;
    }

    public Aluno(Long id) {
        super(id);
    }

    public Aluno(Long id, String nome, String email, String cpf, LocalDate dataNascimento, String telefone, Long matricula) {
        super(id, nome, email, cpf, dataNascimento, telefone);
        this.matricula = matricula;
    }
}
