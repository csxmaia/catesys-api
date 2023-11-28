package com.br.catesysapi.controller.aluno;

import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/aluno")
@AllArgsConstructor
public class AlunoController {

    final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Aluno>>> alunos() {
        List<Aluno> alunoList = alunoService.getAll();

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
