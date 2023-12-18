package com.br.catesysapi.controller.turma;

import com.br.catesysapi.controller.turma.request.SalvarTurmaDTORequest;
import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.entity.Turma;
import com.br.catesysapi.service.TurmaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/turma")
@AllArgsConstructor
public class TurmaController {

    final TurmaService turmaService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Turma>>> turmas() {
        List<Turma> turmaList = turmaService.getAll();

        ApiResponseDTO responseDTO = new ApiResponseDTO(turmaList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping("/professor")
    public ResponseEntity<ApiResponseDTO<List<Turma>>> buscarTurmasPeloProfessorLogado() {
        List<Turma> turma = turmaService.getAllByProfessorLogado();

        ApiResponseDTO responseDTO = new ApiResponseDTO(turma, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Turma>> buscarTurmaPeloId(@PathVariable Long id) {
        Turma turma = turmaService.getById(id);

        ApiResponseDTO responseDTO = new ApiResponseDTO(turma, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Turma>> salvarTurma(@RequestBody SalvarTurmaDTORequest salvarTurmaDTORequest) {
        Turma turmaSalva = null;

        if(salvarTurmaDTORequest.getId() == null) {
            turmaSalva = turmaService.criarTurma(salvarTurmaDTORequest);
        } else {
            turmaSalva = turmaService.editarTurma(salvarTurmaDTORequest);
        }

        ApiResponseDTO responseDTO = new ApiResponseDTO(turmaSalva, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
