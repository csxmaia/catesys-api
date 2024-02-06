package com.br.catesysapi.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Responsavel extends Pessoa{
    @Column
    private String parentesco;
    @Column
    private Boolean batismo;
    @Column
    private Boolean eucaristia;
    @Column
    private Boolean crisma;
    @Column
    private Boolean matrimonio;
    @Column
    private Boolean respostaSacramento;
    @Column
    private String respostaSacramentoComentario;
    @Column
    private Boolean pastoral;
    @Column
    private String pastoralComentario;
    @Column
    private Boolean respostaPastoral;
    @Column
    private String respostaPastoralComentario;
    @Column
    private Boolean missa;
    @Column
    private Boolean missaComentario;


    public Responsavel(Long id) {
        super(id);
    }

    public Responsavel(Long id, String nome, String email, String cpf, LocalDate dataNascimento, String telefone, Long matricula) {
        super(id, nome, email, cpf, dataNascimento, telefone);
    }
}
