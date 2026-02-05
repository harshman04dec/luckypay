package com.assignment.luckypay.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String msg) {
        super(msg);
    }
    public LoanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
