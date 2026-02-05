package com.assignment.luckypay.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class EmiDetailDto {
    private String month;
    private BigDecimal emiAmount;
    private boolean paidStatus;
    private boolean dueStatus;

    // getters & setters
}
