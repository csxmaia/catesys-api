package com.br.catesysapi.repository;

import com.br.catesysapi.entity.Aula;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface AulaRepository extends JpaRepository<Aula, Long> {
    List<Aula> findByTurma_Id(Long id);

    @Modifying
    @Query(value = "insert into aula_alunos(aula_id, aluno_id) values(:aulaId, :alunoId);", nativeQuery = true)
    void saveAulaAluno(@Param("aulaId") Long aulaId, @Param("alunoId") Long alunoId);


}
