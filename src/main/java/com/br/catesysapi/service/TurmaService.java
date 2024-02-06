package com.br.catesysapi.service;

import com.br.catesysapi.controller.turma.request.SalvarTurmaDTORequest;
import com.br.catesysapi.domain.vo.AlunoVO;
import com.br.catesysapi.domain.entity.Aluno;
import com.br.catesysapi.domain.entity.Professor;
import com.br.catesysapi.domain.entity.Turma;
import com.br.catesysapi.domain.entity.Usuario;
import com.br.catesysapi.repository.AlunoRepository;
import com.br.catesysapi.repository.ProfessorRepository;
import com.br.catesysapi.repository.TurmaRepository;
import com.br.catesysapi.security.JwtService;
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
    final AlunoRepository alunoRepository;
    final JwtService jwtService;

    public List<Turma> getAll() {
        List<Turma> turmaList = turmaRepository.findAll();

        

        return turmaList;
    }

    public List<Turma> getAllByProfessorLogado() {
        Usuario usuario = jwtService.getUsuarioEntityFromContext().get();
        List<Turma> turmaList = turmaRepository.findByProfessor_Id(usuario.getId());
        if(turmaList.isEmpty()) {
            throw new RuntimeException("Nenhuma turma encontrada");
        }
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

        turma.setDiaSemana(salvarTurmaDTORequest.getDiaSemana());
        turma.setProfessor(professor.get());
        turma.setHorario(horario);

        Turma turmaCadastrada = turmaRepository.save(turma);

        List<Long> alunosIdsParaAdiconarNaTurma = new ArrayList<>();
        for(AlunoVO alunoVO: salvarTurmaDTORequest.getAlunos()) {
            alunosIdsParaAdiconarNaTurma.add(alunoVO.getId());
        }

        alunoRepository.saveAlunosTurma(alunosIdsParaAdiconarNaTurma, turmaCadastrada.getId());

        return turmaCadastrada;
    }

    public Turma editarTurma(SalvarTurmaDTORequest salvarTurmaDTORequest) {
        Turma turma = new Turma(salvarTurmaDTORequest.getId());
        List<Aluno> alunosTurma = alunoRepository.findByTurma_Id(turma.getId());

        Optional<Professor> professor = professorRepository.findById(salvarTurmaDTORequest.getProfessorId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horario = LocalTime.parse(salvarTurmaDTORequest.getHorario(), formatter);

        turma.setDiaSemana(salvarTurmaDTORequest.getDiaSemana());
        turma.setProfessor(professor.get());
        turma.setHorario(horario);

        Turma turmaEditada = turmaRepository.save(turma);

        List<Long> alunosIdsParaAdiconarNaTurma = new ArrayList<>();
        for(AlunoVO alunoVO: salvarTurmaDTORequest.getAlunos()) {
            alunosIdsParaAdiconarNaTurma.add(alunoVO.getId());
        }

        List<Long> alunosIdsParaRemoverDaTurma = new ArrayList<>();
        for(Aluno alunoTurma: alunosTurma) {
            boolean found = false;
            for(AlunoVO alunoTurmaVO: salvarTurmaDTORequest.getAlunos()) {
                if(alunoTurmaVO.getId() == alunoTurma.getId()) {
                    found = true;
                }
            }
            if(found == false) {
                alunosIdsParaRemoverDaTurma.add(alunoTurma.getId());
            }
        }

        alunoRepository.saveAlunosTurma(alunosIdsParaAdiconarNaTurma, turmaEditada.getId());
        alunoRepository.saveAlunosTurma(alunosIdsParaRemoverDaTurma, null);

        return turmaEditada;
    }
}
