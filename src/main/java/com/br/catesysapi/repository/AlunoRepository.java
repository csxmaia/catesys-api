package com.br.catesysapi.repository;

import com.br.catesysapi.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAllByNomeContainingOrMatriculaContaining(String nome, Long matricula);
    List<Aluno> findAllByNomeContaining(String nome);

//    @Query(value = "SELECT a.*, p.* FROM aluno a JOIN pessoa p ON a.id = p.id INNER JOIN turma_alunos ta ON a.id = ta.aluno_id WHERE ta.turma_id = :turmaId", nativeQuery = true)
//    List<Aluno> findAllAlunoByTurma_Id(@Param("turmaId") Long turmaId);
    List<Aluno> findByTurma_IdOrderByIdDesc(Long turmaId);

    List<Aluno> findAllByOrderByIdDesc();
    @Transactional
    @Modifying
    @Query(value = "UPDATE aluno SET turma_id = :turmaId WHERE id IN (:alunoIds)", nativeQuery = true)
    void saveAlunosTurma(List<Long> alunoIds, Long turmaId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO evento_alunos(aluno_id, evento_id) VALUES (:alunoId, :eventoId)", nativeQuery = true)
    void saveAlunoEvento(Long alunoId, Long eventoId);

    @Transactional
    @Modifying
    @Query(value = "SELECT aluno.*, pessoa.* FROM aluno INNER JOIN pessoa on aluno.id = pessoa.id INNER JOIN evento_alunos ON aluno.id = evento_alunos.aluno_id WHERE evento_alunos.evento_id = :eventoId", nativeQuery = true)
    List<Aluno> alunosPresentesEvento(Long eventoId);
}
