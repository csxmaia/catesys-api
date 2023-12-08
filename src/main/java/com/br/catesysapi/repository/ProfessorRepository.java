package com.br.catesysapi.repository;

import com.br.catesysapi.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
