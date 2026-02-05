package com.assignment.luckypay.model.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        String message,
        LocalDateTime timestamp
) {}
