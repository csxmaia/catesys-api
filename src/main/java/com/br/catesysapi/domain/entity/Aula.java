package com.br.catesysapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "aula_alunos",
            joinColumns = @JoinColumn(name = "aula_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Collection<Aluno> alunos = new ArrayList<>();
}
