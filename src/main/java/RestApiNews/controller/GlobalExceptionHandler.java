package RestApiNews.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 401 — Unauthorized
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return new ResponseEntity<>("Неавторизован: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // 403 — Forbidden
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("Доступ запрещён: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    // 409 — Conflict
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleConflict(IllegalStateException ex) {
        return new ResponseEntity<>("Конфликт: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    // 400 — Ошибки валидации
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return errors;
    }

    // 400 — Неверные аргументы
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>("Неверный аргумент: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 405 — Метод запроса не поддерживается
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity<>("Метод не поддерживается: " + ex.getMethod(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // 404 — Ресурс не найден
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> handleNotFound(NoHandlerFoundException ex) {
        return new ResponseEntity<>("Ресурс не найден: " + ex.getRequestURL(), HttpStatus.NOT_FOUND);
    }

    // 500 — Общая ошибка
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("Внутренняя ошибка: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Catch-all
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Непредвиденная ошибка: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
