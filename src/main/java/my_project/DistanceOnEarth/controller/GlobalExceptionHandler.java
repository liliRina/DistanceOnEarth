package my_project.DistanceOnEarth.controller;

import lombok.extern.slf4j.Slf4j;
import my_project.DistanceOnEarth.exceptions.AmbiguousAddressException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.dao.DataAccessResourceFailureException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<String> handleWebClientError(WebClientException ex) {
        log.error("Ошибка WebClient: {}", ex.getMessage(), ex);
        return ResponseEntity.internalServerError().body("Внутренняя ошибка сервера");

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        log.warn(ex.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(AmbiguousAddressException.class)
    public ResponseEntity<String> handleAmbiguousAddressException(AmbiguousAddressException ex){
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body("Неточный адрес или отсутствующий в базе");
    }
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<String> handleDbUnavailable(DataAccessResourceFailureException e) {
        log.error("Ошибка при подключении к бд: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Сервис временно недоступен");
    }
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDbError(DataAccessException e) {
        log.error("Ошибка БД: ", e);
        return ResponseEntity.internalServerError().body("Внутренняя ошибка сервера");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        log.error("Неожиданная ошибка: {}", ex.getMessage(), ex);
        return ResponseEntity.internalServerError().body("Внутренняя ошибка сервера");
    }
}
