package com.br.catesysapi.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String nome;

    @Column
    private String tipo;

    @Column
    private LocalDateTime horario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "evento_alunos",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Collection<Aluno> alunos = new ArrayList<>();

    private boolean finalizado;

}
