package com.br.catesysapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "etapa")
    private String etapa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "dia_semana")
    private String diaSemana;

    @Column(name = "horario")
    private LocalTime horario;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "turma_alunos",
//            joinColumns = @JoinColumn(name = "turma_id"),
//            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
//    private Collection<Aluno> alunos = new ArrayList<>();

    public Turma(Long id) {
        this.id = id;
    }
}
