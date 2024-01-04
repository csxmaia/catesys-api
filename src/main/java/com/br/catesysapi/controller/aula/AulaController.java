package com.br.catesysapi.controller.aula;

import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.entity.Aula;
import com.br.catesysapi.service.AulaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/aula")
@AllArgsConstructor
public class AulaController {

    final AulaService aulaService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Aula>>> aulas(@RequestParam(required = false) String term) {
        List<Aula> aulaList = aulaService.getAll();

        ApiResponseDTO responseDTO = new ApiResponseDTO(aulaList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping("/turma/{turmaId}")
    public ResponseEntity<ApiResponseDTO<List<Aula>>> aulasByTurmaId(@PathVariable Long turmaId) {
        List<Aula> aulaList = aulaService.getAllByTurmaId(turmaId);

        ApiResponseDTO responseDTO = new ApiResponseDTO(aulaList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping("/{aulaId}/presenca/{alunoId}")
    public ResponseEntity<ApiResponseDTO<List<Aula>>> aulasByTurmaId(@PathVariable Long aulaId, @PathVariable Long alunoId) {
        aulaService.registrarPresenca(aulaId, alunoId);

        ApiResponseDTO responseDTO = new ApiResponseDTO(HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
