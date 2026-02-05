package com.assignment.luckypay.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanResponse(
        String loanAccountNumber,
        LocalDate dueDate,
        BigDecimal emiAmount
) {}
