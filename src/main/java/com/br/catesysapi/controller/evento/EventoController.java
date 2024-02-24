package com.br.catesysapi.controller.evento;

import com.br.catesysapi.controller.evento.request.RegistrarPresencaAlunoRequest;
import com.br.catesysapi.controller.evento.request.SalvarEventoRequest;
import com.br.catesysapi.domain.entity.Evento;
import com.br.catesysapi.dto.ApiResponseDTO;
import com.br.catesysapi.service.EventoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/evento")
@AllArgsConstructor
public class EventoController {
    private EventoService eventoService;

    @PostMapping("/registrarPresencaAluno")
    public ResponseEntity<ApiResponseDTO> registrarPresencaAluno(@RequestBody RegistrarPresencaAlunoRequest registrarPresencaAlunoRequest) {
        eventoService.registrarPresencaAluno(registrarPresencaAlunoRequest.getEventoId(), registrarPresencaAlunoRequest.getAlunoId());

        ApiResponseDTO responseDTO = new ApiResponseDTO(
                "Presença de " + registrarPresencaAlunoRequest.getAlunoNome() + " confirmada com sucesso!",
                HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO> eventos() {
        List<Evento> eventoList = eventoService.getAll();
        ApiResponseDTO responseDTO = new ApiResponseDTO(eventoList, HttpStatus.OK);
        return ResponseEntity.status(responseDTO.getStatus()). body(responseDTO);
    }

    @GetMapping("/naoFinalizados")
    public ResponseEntity<ApiResponseDTO> eventosNaoFinalizados() {
        List<Evento> eventoList = eventoService.getAllNaoFinalizados();
        ApiResponseDTO responseDTO = new ApiResponseDTO(eventoList, HttpStatus.OK);
        return ResponseEntity.status(responseDTO.getStatus()). body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO> salvarEvento(@RequestBody SalvarEventoRequest salvarEventoRequest) {
        Evento evento = eventoService.salvar(salvarEventoRequest);

        ApiResponseDTO responseDTO = new ApiResponseDTO(evento, HttpStatus.OK);

        return ResponseEntity.status(responseDTO.getStatus()). body(responseDTO);
    }

    @PostMapping("/finalizarEvento/{eventoId}")
    public ResponseEntity<ApiResponseDTO> eventosNaoFinalizados(@PathVariable Long eventoId) {
        eventoService.finalizarEvento(eventoId);
        ApiResponseDTO responseDTO = new ApiResponseDTO("Evento finalizado", HttpStatus.OK);
        return ResponseEntity.status(responseDTO.getStatus()). body(responseDTO);
    }
}
