package com.br.catesysapi.repository;

import com.br.catesysapi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAllByNomeContainingOrMatriculaContaining(String nome, Long matricula);
    List<Aluno> findAllByNomeContaining(String nome);
}
