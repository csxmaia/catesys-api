package com.br.catesysapi.repository;

import com.br.catesysapi.domain.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByProfessor_Id(Long id);

}
