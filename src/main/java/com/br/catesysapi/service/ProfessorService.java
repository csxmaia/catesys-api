package com.br.catesysapi.service;

import com.br.catesysapi.controller.professor.request.SalvarProfessorDTORequest;
import com.br.catesysapi.entity.Professor;
import com.br.catesysapi.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessorService {

    final ProfessorRepository professorRepository;

    final PessoaService pessoaService;

    public List<Professor> getAll() {
        List<Professor> professorList = professorRepository.findAll();
        return professorList;
    }

    public Professor getById(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if(professor.isEmpty()) {
            throw new RuntimeException("Professor não encontrado");
        }
        return professor.get();
    }

    public Professor criarProfessor(SalvarProfessorDTORequest salvarProfessorDTORequest) {
        pessoaService.validarNovaPessoa(salvarProfessorDTORequest.getEmail(), salvarProfessorDTORequest.getCpf());

        Professor professor = new Professor();
        professor.setNome(salvarProfessorDTORequest.getNome());
        professor.setEmail(salvarProfessorDTORequest.getEmail());
        professor.setCpf(salvarProfessorDTORequest.getCpf());
        professor.setDataNascimento(salvarProfessorDTORequest.getDataNascimento());
        professor.setTelefone(salvarProfessorDTORequest.getTelefone());

        Professor professorCadastrado = professorRepository.save(professor);

        return professorCadastrado;
    }

    public Professor editarProfessor(SalvarProfessorDTORequest salvarProfessorDTORequest) {
        Professor professor = new Professor(salvarProfessorDTORequest.getId());

        professor.setNome(salvarProfessorDTORequest.getNome());
        professor.setEmail(salvarProfessorDTORequest.getEmail());
        professor.setCpf(salvarProfessorDTORequest.getCpf());
        professor.setDataNascimento(salvarProfessorDTORequest.getDataNascimento());
        professor.setTelefone(salvarProfessorDTORequest.getTelefone());

        Professor professorEditado = professorRepository.save(professor);

        return professorEditado;
    }


}
