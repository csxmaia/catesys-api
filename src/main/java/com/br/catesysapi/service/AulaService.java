package com.br.catesysapi.service;

import com.br.catesysapi.entity.Aula;
import com.br.catesysapi.repository.AulaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AulaService {
    final AulaRepository aulaRepository;

    public List<Aula> getAll() {
        List<Aula> aulaList = aulaRepository.findAll();
        return aulaList;
    }
}
