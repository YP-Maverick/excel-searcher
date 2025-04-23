package ru.maverick.excelsearcher.exceptions.handler;


import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import ru.maverick.excelsearcher.controller.SearchController;
import ru.maverick.excelsearcher.exceptions.NotFoundException;
import ru.maverick.excelsearcher.utils.DataFormatter;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestControllerAdvice(assignableTypes = {
        SearchController.class
})
public class ControllerExceptionHandler {

    private void log(Throwable e) {
        log.error("Exception {}: {}", e, e.getMessage());
    }

    private Map<String, String> createMap(String status, String reason, String message) {
        return Map.of("status", status,
                "reason", reason,
                "message", message,
                "timestamp", LocalDateTime.now().format(DataFormatter.getFormatter()));
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            ValidationException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValid(final Exception e) {
        log(e);
        return createMap("BAD_REQUEST", "Incorrectly made request", e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(final NotFoundException e) {
        log(e);
        return createMap("NOT_FOUND", "The required object was not found",
                e.getMessage());
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleOtherExc(final Exception e) {
        log(e);
        return createMap("INTERNAL_SERVER_ERROR", "Unexpected error",
                e.getMessage());
    }
}