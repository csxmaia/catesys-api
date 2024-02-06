package com.br.catesysapi.domain.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor extends Pessoa{
    public Professor(Long id) {
        super(id);
    }
}
