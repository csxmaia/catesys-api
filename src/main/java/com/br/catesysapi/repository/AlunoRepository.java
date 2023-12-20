package com.br.catesysapi.repository;

import com.br.catesysapi.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAllByNomeContainingOrMatriculaContaining(String nome, Long matricula);
    List<Aluno> findAllByNomeContaining(String nome);
    @Query(value = "SELECT a.*, p.* FROM aluno a JOIN pessoa p ON a.id = p.id INNER JOIN turma_alunos ta ON a.id = ta.aluno_id WHERE ta.turma_id = :turmaId", nativeQuery = true)
    List<Aluno> findAllAlunoByTurma_Id(@Param("turmaId") Long turmaId);

}
