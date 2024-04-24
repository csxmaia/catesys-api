package com.br.catesysapi.service;

import com.br.catesysapi.controller.evento.request.SalvarEventoRequest;
import com.br.catesysapi.domain.entity.Aluno;
import com.br.catesysapi.domain.entity.Evento;
import com.br.catesysapi.repository.AlunoRepository;
import com.br.catesysapi.repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {
    final AlunoRepository alunoRepository;
    final EventoRepository eventoRepository;

    public void registrarPresencaAluno(Long eventoId, Long alunoId) {
        alunoRepository.saveAlunoEvento(alunoId, eventoId);
    }

    public List<Evento> getAll() {
        List<Evento> eventoList = eventoRepository.findAll();
        return eventoList;
    }

    public Evento salvar(SalvarEventoRequest salvarEventoRequest) {
        Evento evento = new Evento();
        evento.setTipo(salvarEventoRequest.getTipo());
        evento.setHorario(salvarEventoRequest.getHorario());
        Evento eventoList = eventoRepository.save(evento);
        return evento;
    }

    public List<Evento> getAllNaoFinalizados() {
        List<Evento> eventoList = eventoRepository.findByFinalizadoFalse();
        return eventoList;
    }

    public void finalizarEvento(Long eventoId) {
        eventoRepository.finalizarEvento(eventoId);
    }

    public List<Aluno> getAllAlunosPresentes(Long eventoId) {
        return alunoRepository.alunosPresentesEvento(eventoId);
    }
}
