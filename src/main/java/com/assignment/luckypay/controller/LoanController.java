package com.assignment.luckypay.controller;

import com.assignment.luckypay.model.dto.LoanResponse;
import com.assignment.luckypay.service.LoanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    @GetMapping("/{loanAccountNumber}")
    public LoanResponse getLoan(
            @PathVariable String loanAccountNumber) {
        return service.getLoan(loanAccountNumber);
    }
}
