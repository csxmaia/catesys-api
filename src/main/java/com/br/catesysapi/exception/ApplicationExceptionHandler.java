package com.br.catesysapi.exception;

import com.br.catesysapi.dto.ApiResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleException(final Exception ex) {
        log.error("Erro inesperado: Exception: " + ex.getMessage(), ex);
        return new ResponseEntity("Erro inesperado", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponseDTO<Void>> handleValidationException(final ValidationException ex) {
        HttpStatus status = ((ValidationException) ex).getStatus();
        log.error("Erro na validação dos dados: ValidationException" + ex.getMessage(), ex);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(ex.getMessage(), status);
        return ResponseEntity.status(apiResponseDTO.getHttpStatus()).body(apiResponseDTO);
    }
}