package br.com.dxc.seletiva.exception;


import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratar404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratar400(MethodArgumentNotValidException e) {
        List<FieldError> erros = e.getFieldErrors();
        List<CampoInvalido> camposInvalidos = erros.stream().map(CampoInvalido::new)
        													   .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(camposInvalidos);
    }

}
