package com.br.catesysapi.controller.aluno;

import com.br.catesysapi.controller.aluno.request.CadastrarAlunoDTORequest;
import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.entity.Aluno;
import com.br.catesysapi.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/aluno")
@AllArgsConstructor
public class AlunoController {

    final AlunoService alunoService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Aluno>>> alunos(@RequestParam(required = false) String term) {
        List<Aluno> alunoList = new ArrayList<>();

        if(term != null) {
            alunoList = alunoService.getAllByTerm(term);
        } else {
            alunoList = alunoService.getAll();
        }

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

   /* @GetMapping()
    public ResponseEntity<ApiResponseDTO<List<Aluno>>> alunosBusca(@RequestParam String term) {
        List<Aluno> alunoList = alunoService.getAllByTerm(term);

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }*/

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Aluno>> cadastrarAluno(@RequestBody CadastrarAlunoDTORequest cadastrarAlunoDTORequest) {
        Aluno alunoCadastrado = alunoService.criarAluno(cadastrarAlunoDTORequest);

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoCadastrado, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
