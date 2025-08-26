package com.devsu.hackerearth.backend.account.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.devsu.hackerearth.backend.account.constants.AccountConstants;
import com.devsu.hackerearth.backend.account.model.dto.ResponseErrorDto;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ResponseErrorDto> notFoundException(Exception exception) {
        ResponseErrorDto response = ResponseErrorDto.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .error(AccountConstants.NOT_FOUND)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({ NonExecutableTransactionExeption.class })
    public ResponseEntity<ResponseErrorDto> nonExecutableTransactionExeption(Exception exception) {
        ResponseErrorDto response = ResponseErrorDto.builder()
                .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .error(AccountConstants.UNPROCESABLE_ENTITY)
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<ResponseErrorDto> alreadyExistException(Exception exception){
        ResponseErrorDto response = ResponseErrorDto.builder()
        .code(HttpStatus.CONFLICT.value())
        .error(AccountConstants.ALREADY_EXIST)
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<ResponseErrorDto> messageNotReadableException(Exception exception){
        ResponseErrorDto response = ResponseErrorDto.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .error(AccountConstants.BAD_REQUEST)
        .message(exception.getMessage())
        .timestamp(LocalDateTime.now())
        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseErrorDto> constraintViolationException(ConstraintViolationException exception){
        ResponseErrorDto response = ResponseErrorDto.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .error(AccountConstants.INVALID_INPUT_DATA)
        .message(exception.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", ")))
        .timestamp(LocalDateTime.now())
        .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
