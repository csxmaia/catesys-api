package com.br.catesysapi.repository;

import com.br.catesysapi.domain.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByFinalizadoFalse();
    List<Evento> findAllByOrderByIdDesc();

    @Transactional
    @Modifying
    @Query(value = "UPDATE evento SET finalizado = true WHERE id = :eventoId", nativeQuery = true)
    void finalizarEvento(Long eventoId);

}
