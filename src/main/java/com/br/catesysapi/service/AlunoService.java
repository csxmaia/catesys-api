package com.br.catesysapi.service;

import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    final AlunoRepository alunoRepository;

    public List<Aluno> getAll() {
        List<Aluno> alunoList = alunoRepository.findAll();
        return alunoList;
    }

}
