package com.br.catesysapi.service;

import com.br.catesysapi.controller.turma.request.SalvarTurmaDTORequest;
import com.br.catesysapi.dto.AlunoVO;
import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.entity.Professor;
import com.br.catesysapi.entity.Turma;
import com.br.catesysapi.repository.ProfessorRepository;
import com.br.catesysapi.repository.TurmaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TurmaService {
    final TurmaRepository turmaRepository;
    final ProfessorRepository professorRepository;

    public List<Turma> getAll() {
        List<Turma> turmaList = turmaRepository.findAll();
        return turmaList;
    }

    public Turma getById(Long id) {
        Optional<Turma> turma = turmaRepository.findById(id);
        if(turma.isEmpty()) {
            throw new RuntimeException("Turma não encontrada");
        }
        return turma.get();
    }

    public Turma criarTurma(SalvarTurmaDTORequest salvarTurmaDTORequest) {
        Turma turma = new Turma();

        Optional<Professor> professor = professorRepository.findById(salvarTurmaDTORequest.getProfessorId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horario = LocalTime.parse(salvarTurmaDTORequest.getHorario(), formatter);

        List<Aluno> alunos = new ArrayList<>();
        for(AlunoVO alunoVO: salvarTurmaDTORequest.getAlunos()) {
            alunos.add(new Aluno(alunoVO.getId()));
        }

        turma.setDiaSemana(salvarTurmaDTORequest.getDiaSemana());
        turma.setProfessor(professor.get());
        turma.setHorario(horario);
        turma.setAlunos(alunos);

        Turma turmaCadastrada = turmaRepository.save(turma);

        return turmaCadastrada;
    }

    public Turma editarTurma(SalvarTurmaDTORequest salvarTurmaDTORequest) {
        Turma turma = new Turma(salvarTurmaDTORequest.getId());

        Optional<Professor> professor = professorRepository.findById(salvarTurmaDTORequest.getProfessorId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horario = LocalTime.parse(salvarTurmaDTORequest.getHorario(), formatter);

        List<Aluno> alunos = new ArrayList<>();
        for(AlunoVO alunoVO: salvarTurmaDTORequest.getAlunos()) {
            alunos.add(new Aluno(alunoVO.getId()));
        }

        turma.setDiaSemana(salvarTurmaDTORequest.getDiaSemana());
        turma.setProfessor(professor.get());
        turma.setHorario(horario);
        turma.setAlunos(alunos);

        Turma turmaEditada = turmaRepository.save(turma);

        return turmaEditada;
    }
}
