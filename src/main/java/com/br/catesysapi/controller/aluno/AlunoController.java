package com.br.catesysapi.controller.aluno;

import com.br.catesysapi.controller.aluno.request.SalvarAlunoDTORequest;
import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.domain.entity.Aluno;
import com.br.catesysapi.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/aluno")
@AllArgsConstructor
public class AlunoController {

    @Autowired
    private ResourceLoader resourceLoader;

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Aluno>> buscarAlunoPeloId(@PathVariable Long id) {
        Aluno aluno = alunoService.getById(id);

        ApiResponseDTO responseDTO = new ApiResponseDTO(aluno, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping("/turma/{id}")
    public ResponseEntity<ApiResponseDTO<Aluno>> buscarAlunosPeloTurmaId(@PathVariable Long id) {
        List<Aluno> alunoList = alunoService.getAllByTurmaId(id);

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoList, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<Aluno>> salvarAluno(@RequestBody SalvarAlunoDTORequest salvarAlunoDTORequest) {
        Aluno alunoCadastrado = null;
        try {

        if(salvarAlunoDTORequest.getId() == null) {
            alunoCadastrado = alunoService.criarAluno(salvarAlunoDTORequest);
        } else {
            alunoCadastrado = alunoService.editarAluno(salvarAlunoDTORequest);
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ApiResponseDTO responseDTO = new ApiResponseDTO(alunoCadastrado, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage() throws IOException {
        // Load PNG file from resources folder
        Resource resource = resourceLoader.getResource("classpath:static/images/202300004.png");
        Path path = Paths.get(resource.getURI());
        byte[] imageData = Files.readAllBytes(path);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
    }
}
