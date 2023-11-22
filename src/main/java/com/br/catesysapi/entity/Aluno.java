package com.br.catesysapi.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Aluno extends Pessoa{
    private Long matricula;
}
