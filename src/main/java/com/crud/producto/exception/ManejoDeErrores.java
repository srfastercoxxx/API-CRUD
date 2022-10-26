package com.crud.producto.exception;

import com.crud.producto.viewmodel.ErrorGenerico;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

@ControllerAdvice()
public class ManejoDeErrores {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorGenerico> manejarParametroObligatorioFaltante(
            MissingServletRequestParameterException ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("parametros_faltantes");
        err.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorGenerico> manejarBeanValidationError(ConstraintViolationException ex) {
        StringBuilder msg = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
            msg.append(constraintViolation.getPropertyPath().toString()).append(" - ").append(constraintViolation.getMessage()).append(".\n");
        }
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("parametros_formato_invalido");
        err.setMessage(msg.toString());
        return ResponseEntity.badRequest().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> manejarExcepcionPayload (HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            return ResponseEntity.status(400).body("tipo de dato invalido..." );
        }
        if (ex.getCause() instanceof JsonParseException) {
            return ResponseEntity.status(400).body("json mal cerrado..." );
        }
        return ResponseEntity.status(400).body("mensaje ilegible..." );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorGenerico> manejarErrorNoControlado(Exception ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("error_no_controlado");
        err.setMessage("Para mas informacion revise logs.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    @ExceptionHandler(java.net.SocketException.class)
    public ResponseEntity<ErrorGenerico> gateway(java.net.SocketException ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("error_time_out");
        err.setMessage("Problemas de respuesta");
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(err);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ErrorGenerico> castError(HttpServerErrorException.InternalServerError ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("error_interno");
        err.setMessage("Error Interno");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorGenerico> manejarBeanValidationMethod(MethodArgumentNotValidException ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("error_en_formato");
        err.setMessage("Error parametro invalido");
        return ResponseEntity.badRequest().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(err);
    }

    @ExceptionHandler(org.springframework.web.client.ResourceAccessException.class)
    public ResponseEntity<ErrorGenerico> manejotimeout(org.springframework.web.client.ResourceAccessException ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("error_timeout");
        err.setMessage("Error en tiempo de respuesta");
        return ResponseEntity.badRequest().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(err);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorGenerico> manejarErrorValidacion(ValidationException ex) {
        ErrorGenerico err = new ErrorGenerico();
        err.setCode("validation_exception");
        err.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorGenerico> manejarHttpServerErrorException(HttpServerErrorException ex) {
        ErrorGenerico err = new ErrorGenerico().jsonParser(ex.getResponseBodyAsString());
        if(err == null){
            err = new ErrorGenerico();
            err.setCode("error_general");
            err.setMessage("Error Generico");
        }
        return ResponseEntity.status(ex.getStatusCode()).body(err);
    }

}
