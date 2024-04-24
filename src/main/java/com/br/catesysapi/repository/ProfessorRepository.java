package com.br.catesysapi.repository;

import com.br.catesysapi.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findAllByAtivoTrueOrderByIdDesc();

    Optional<Professor> findByIdAndAtivoTrue(Long id);
}
