package com.fernandez.basketball.matchapi.match.exception;

import com.fernandez.basketball.matchapi.match.dto.MatchDtos;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(AppException.class)
    public ResponseEntity<MatchDtos.ErrorResponse> handleAppException(
            AppException exception, HttpServletRequest request) {
        var errorCode = exception.errorCode();
        var response = new MatchDtos.ErrorResponse(
                errorCode.name(),
                errorCode.message(),
                Instant.now(),
                request.getRequestURI());

        log.warn("{} on {}", errorCode.name(), request.getRequestURI());

        return ResponseEntity.status(errorCode.status()).body(response);
    }
}
