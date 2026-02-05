package com.assignment.luckypay.exception;

import com.assignment.luckypay.model.dto.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(
            LoanNotFoundException ex) {

        log.warn("Loan not found: {}", ex.getMessage());
        return new ApiErrorResponse(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ExternalServiceException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ApiErrorResponse handleExternalError(
            ExternalServiceException ex) {

        log.error("External service error", ex);
        return new ApiErrorResponse(
                "Upstream service failure",
                LocalDateTime.now());
    }
}
