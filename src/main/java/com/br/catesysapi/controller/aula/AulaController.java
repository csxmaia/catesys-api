package com.br.catesysapi.controller.aula;

import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.entity.Aula;
import com.br.catesysapi.service.AulaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/aula")
@AllArgsConstructor
public class AulaController {

    final AulaService aulaService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Aluno>>> alunos(@RequestParam(required = false) String term) {
        List<Aula> alunoList = aulaService.getAll();

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
