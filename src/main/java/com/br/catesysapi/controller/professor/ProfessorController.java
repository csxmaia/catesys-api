package com.br.catesysapi.controller.professor;

import com.br.catesysapi.controller.professor.request.SalvarProfessorDTORequest;
import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.domain.entity.Professor;
import com.br.catesysapi.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professor")
@AllArgsConstructor
public class ProfessorController {

    final ProfessorService professorService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Professor>>> professores() {
        List<Professor> professorList = professorService.getAll();

        ApiResponseDTO responseDTO = new ApiResponseDTO(professorList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Professor>> buscarProfessorPeloId(@PathVariable Long id) {
        Professor professor = professorService.getById(id);

        ApiResponseDTO responseDTO = new ApiResponseDTO(professor, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Professor>> salvarProfessor(@RequestBody SalvarProfessorDTORequest salvarProfessorDTORequest) {
        Professor professorCadastrado = null;

        if(salvarProfessorDTORequest.getId() == null) {
            professorCadastrado = professorService.criarProfessor(salvarProfessorDTORequest);
        } else {
            professorCadastrado = professorService.editarProfessor(salvarProfessorDTORequest);
        }

        ApiResponseDTO responseDTO = new ApiResponseDTO(professorCadastrado, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @PostMapping("/{id}/inativar")
    public ResponseEntity<ApiResponseDTO<Void>> inativarProfessor(@PathVariable Long id) {
        professorService.inativarProfessor(id);

        ApiResponseDTO responseDTO = new ApiResponseDTO("Professor inativado com sucesso", HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }
}
