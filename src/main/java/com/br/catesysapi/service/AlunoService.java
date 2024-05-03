package com.br.catesysapi.service;

import com.br.catesysapi.controller.aluno.request.SalvarAlunoDTORequest;
import com.br.catesysapi.domain.entity.Aluno;
import com.br.catesysapi.domain.entity.Turma;
import com.br.catesysapi.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    final AlunoRepository alunoRepository;

    final PessoaService pessoaService;

    public List<Aluno> getAll() {
        List<Aluno> alunoList = alunoRepository.findAllByOrderByIdDesc();
        return alunoList;
    }

    public List<Aluno> getAllByTurmaId(Long turmaId) {
        List<Aluno> alunoList = alunoRepository.findByTurma_IdOrderByIdDesc(turmaId);
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

    public Aluno criarAluno(SalvarAlunoDTORequest salvarAlunoDTORequest) throws IOException {
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

        inserirImagemAluno(salvarAlunoDTORequest.getFotoBase64(), alunoCadastrado);

        alunoRepository.save(alunoCadastrado);

        return alunoCadastrado;
    }

    public Aluno editarAluno(SalvarAlunoDTORequest salvarAlunoDTORequest) throws IOException {
        Aluno aluno = alunoRepository.findById(salvarAlunoDTORequest.getId()).get();

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

        Aluno alunoAlterado = alunoRepository.save(aluno);

        inserirImagemAluno(salvarAlunoDTORequest.getFotoBase64(), alunoAlterado);

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

    private void inserirImagemAluno(String fotoBase64, Aluno aluno) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(fotoBase64.getBytes(StandardCharsets.UTF_8));

        String directoryPath = "src/main/resources/static/images";
        if (!Files.exists(Paths.get(directoryPath))) {
            Files.createDirectories(Paths.get(directoryPath));
        }

        String fileName = aluno.getMatricula().toString() + ".png";

        File file = new File(directoryPath, fileName);

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            bos.write(decodedBytes);
            aluno.setFotoUrl(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
