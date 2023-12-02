package com.br.catesysapi.service;

import com.br.catesysapi.controller.aluno.request.CadastrarAlunoDTORequest;
import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    final AlunoRepository alunoRepository;

    public List<Aluno> getAll() {
        List<Aluno> alunoList = alunoRepository.findAll();
        return alunoList;
    }

    public Aluno criarAluno(CadastrarAlunoDTORequest cadastrarAlunoDTORequest) {
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
        Long ano = (long) new Date().getYear();

        Long quantidadeDe0 = (long) (5 - String.valueOf(idAluno).length());
        String zeros = null;
        for(int i = 0; i < quantidadeDe0; i++) {
            zeros = zeros + "0";
        }

        String matricula = String.valueOf(ano) + zeros + String.valueOf(idAluno);

        return Long.parseLong(matricula);
    }

}
