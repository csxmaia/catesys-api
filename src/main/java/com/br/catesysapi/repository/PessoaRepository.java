package com.br.catesysapi.repository;

import com.br.catesysapi.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findPessoaByEmailAndAtivoTrue(String email);

    Optional<Pessoa> findPessoaByCpfAndAtivoTrue(String cpf);
}
