package com.assignment.luckypay.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ExternalLoanResponse {
    private String loanAccountNumber;
    private List<EmiDetailDto> emiDetails;

    // getters & setters
}
