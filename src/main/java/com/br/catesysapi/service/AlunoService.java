package com.br.catesysapi.service;

import com.br.catesysapi.controller.aluno.request.SalvarAlunoDTORequest;
import com.br.catesysapi.domain.entity.Aluno;
import com.br.catesysapi.domain.entity.Turma;
import com.br.catesysapi.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    final AlunoRepository alunoRepository;

    final PessoaService pessoaService;

    public List<Aluno> getAll() {
        List<Aluno> alunoList = alunoRepository.findAll();
        return alunoList;
    }

    public List<Aluno> getAllByTurmaId(Long turmaId) {
        List<Aluno> alunoList = alunoRepository.findByTurma_Id(turmaId);
        return alunoList;
    }

    public Aluno getById(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if(aluno.isEmpty()) {
            throw new RuntimeException("Aluno não encontrado");
        }
        return aluno.get();
    }

    public List<Aluno> getAllByTerm(String term) {
        Long matriculaTerm = null;

        try {
            matriculaTerm = Long.parseLong(term);
        } catch (NumberFormatException e) {
        }

        List<Aluno> alunoList = new ArrayList<>();

        if(matriculaTerm != null) {
            alunoList = alunoRepository.findAllByNomeContainingOrMatriculaContaining(term, matriculaTerm);
        } else {
            alunoList = alunoRepository.findAllByNomeContaining(term);
            alunoList.sort((a1, a2) -> {

                if (a1.getNome().toLowerCase().startsWith(term.toLowerCase()) && !a2.getNome().toLowerCase().startsWith(term.toLowerCase())) {
                    return -1;
                } else if (!a1.getNome().toLowerCase().startsWith(term.toLowerCase()) && a2.getNome().toLowerCase().startsWith(term.toLowerCase())) {
                    return 1;
                }
                return 0;
            });
        }

        return alunoList;
    }

    public Aluno criarAluno(SalvarAlunoDTORequest salvarAlunoDTORequest) {
        Aluno aluno = new Aluno();
        aluno.setNome(salvarAlunoDTORequest.getNome());
        aluno.setTelefone(salvarAlunoDTORequest.getTelefone());
        aluno.setDataNascimento(salvarAlunoDTORequest.getDataNascimento());
        aluno.setBatizadoData(salvarAlunoDTORequest.getBatizadoData());
        aluno.setBatizadoParoquia(salvarAlunoDTORequest.getBatizadoParoquia());
        aluno.setCondicaoSaudeComportamental(salvarAlunoDTORequest.getCondicaoSaudeComportamental());
        aluno.setRua(salvarAlunoDTORequest.getRua());
        aluno.setNumero(salvarAlunoDTORequest.getNumero());
        aluno.setBairro(salvarAlunoDTORequest.getBairro());
        aluno.setComplemento(salvarAlunoDTORequest.getComplemento());
        aluno.setCidade(salvarAlunoDTORequest.getCidade());
        aluno.setTurma(new Turma(salvarAlunoDTORequest.getId()));

        Aluno alunoCadastrado = alunoRepository.save(aluno);

        alunoCadastrado.setMatricula(criarNumeroMatricula(alunoCadastrado.getId()));

        alunoRepository.save(alunoCadastrado);

        return alunoCadastrado;
    }

    public Aluno editarAluno(SalvarAlunoDTORequest salvarAlunoDTORequest) {
        Aluno aluno = new Aluno(salvarAlunoDTORequest.getId());

        aluno.setNome(salvarAlunoDTORequest.getNome());
        aluno.setTelefone(salvarAlunoDTORequest.getTelefone());
        aluno.setDataNascimento(salvarAlunoDTORequest.getDataNascimento());
        aluno.setBatizadoData(salvarAlunoDTORequest.getBatizadoData());
        aluno.setBatizadoParoquia(salvarAlunoDTORequest.getBatizadoParoquia());
        aluno.setCondicaoSaudeComportamental(salvarAlunoDTORequest.getCondicaoSaudeComportamental());
        aluno.setRua(salvarAlunoDTORequest.getRua());
        aluno.setNumero(salvarAlunoDTORequest.getNumero());
        aluno.setBairro(salvarAlunoDTORequest.getBairro());
        aluno.setComplemento(salvarAlunoDTORequest.getComplemento());
        aluno.setCidade(salvarAlunoDTORequest.getCidade());

        Aluno alunoAlterado = alunoRepository.save(aluno);

        alunoAlterado.setMatricula(criarNumeroMatricula(alunoAlterado.getId()));

        alunoRepository.save(alunoAlterado);

        return alunoAlterado;
    }

    private Long criarNumeroMatricula(Long idAluno) {
        Long ano = (long) LocalDate.now().getYear();

        Long quantidadeDe0 = (long) (5 - String.valueOf(idAluno).length());
        String zeros = "";
        for(int i = 0; i < quantidadeDe0; i++) {
            zeros = zeros + "0";
        }

        String matricula = String.valueOf(ano) + zeros + String.valueOf(idAluno);

        return Long.parseLong(matricula);
    }

}
