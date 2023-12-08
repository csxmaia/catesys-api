package com.br.catesysapi.repository;

import com.br.catesysapi.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
