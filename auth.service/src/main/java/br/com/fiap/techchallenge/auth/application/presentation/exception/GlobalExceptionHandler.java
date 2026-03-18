package br.com.fiap.techchallenge.auth.application.presentation.exception;

import br.com.fiap.techchallenge.auth.domain.exception.UsuarioJaExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import br.com.fiap.techchallenge.auth.application.dto.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioJaExisteException.class)
    public ResponseEntity<?> usuarioJaExiste(UsuarioJaExisteException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ex.getMessage()));
    }

}