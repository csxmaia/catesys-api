package com.br.catesysapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {
    @JsonProperty
    private int status;
    @JsonProperty
    private Boolean success;
    @JsonProperty
    private String message;
    @JsonProperty
    private T data;

    public ApiResponseDTO(T data, String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.success = status.is2xxSuccessful();
        this.data = data;
    }

    public ApiResponseDTO(T data, HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
        this.data = data;
    }

    public ApiResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.success = status.is2xxSuccessful();
    }

    public ApiResponseDTO(HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
        this.success = status.is2xxSuccessful();
    }

    public void setSuccess(boolean success) {}
}
