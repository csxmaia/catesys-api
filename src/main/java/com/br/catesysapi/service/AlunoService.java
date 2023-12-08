package com.br.catesysapi.service;

import com.br.catesysapi.controller.aluno.request.CadastrarAlunoDTORequest;
import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    final AlunoRepository alunoRepository;

    final PessoaService pessoaService;

    public List<Aluno> getAll() {
        List<Aluno> alunoList = alunoRepository.findAll();
        return alunoList;
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

    public Aluno criarAluno(CadastrarAlunoDTORequest cadastrarAlunoDTORequest) {
        pessoaService.validarNovaPessoa(cadastrarAlunoDTORequest.getEmail(), cadastrarAlunoDTORequest.getCpf());

        Aluno aluno = new Aluno();
        aluno.setNome(cadastrarAlunoDTORequest.getNome());
        aluno.setEmail(cadastrarAlunoDTORequest.getEmail());
        aluno.setCpf(cadastrarAlunoDTORequest.getCpf());
        aluno.setDataNascimento(cadastrarAlunoDTORequest.getDataNascimento());
        aluno.setTelefone(cadastrarAlunoDTORequest.getTelefone());

        Aluno alunoCadastrado = alunoRepository.save(aluno);

        alunoCadastrado.setMatricula(criarNumeroMatricula(alunoCadastrado.getId()));

        alunoRepository.save(alunoCadastrado);

        return alunoCadastrado;
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
